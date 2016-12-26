package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import Exceptions.ArithmeticError;
import Expression.Expression;
import Expression.Composite;
import Expression.Number.ImproperFraction;
import Expression.Number.Rational;
import Expression.Operator.Add;
import Expression.Operator.Divide;
import Expression.Operator.Multiply;
import Expression.Operator.Subtract;
import Expression.Primitive;
import Expression.Operator.Operator;

/**
 * Class to represent a Parser for my Language. Does nothing right now.
 */
public class Parser {

  private Scanner scan;

  /**
   * Creates a Parser that reads from given Readable.
   * @param input the readable from which to read code.
   */
  public Parser(Readable input) {

    try {
      Objects.requireNonNull(input);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("Input Stream provided was null.");
    }

    this.scan = new Scanner(input);
    this.scan.useDelimiter("");
  }

  /**
   * Parse the code to get a list of expressions.
   * @return the list of all expressions in input stream.
   */
  public List<Expression> parseCode() {
    List<Expression> expressions = new ArrayList<Expression>();

    while (this.scan.hasNext()) {
      String excerpt = this.getExcerpt();
      expressions.add(this.getExpression(excerpt));
    }

    return expressions;
  }

  /**
   * Get an "excerpt", defined as one open parenthesis to its corresponding closing parenthesis
   * from input.
   * @return the excerpt from input as a String.
   */
  private String getExcerpt() {

    StringBuilder excerpt = new StringBuilder("");

    int numParenth = 0;
    boolean isComposite = false;
    String c = this.scan.next();
    while (this.scan.hasNext() && c.equals(" ")) {
      c = this.scan.next();
    }

    if (c.equals("(")) {
      numParenth++;
      isComposite = true;
    }
    excerpt.append(c);
    while (this.scan.hasNext()
            && (isComposite && numParenth > 0 || !isComposite && !c.equals(" "))) {
      c = this.scan.next();

      if (c.equals("(")) {
        numParenth++;
      }
      else if (c.equals(")")) {
        numParenth--;
      }

      excerpt.append(c);
    }

    return excerpt.toString();
  }

  /**
   * Parse the code to get a single expression from the code.
   * @param excerpt  the String form of the Expression to be parsed.
   * @return a single Expression parsed from the input.
   */
  private Expression getExpression(String excerpt) {

    int i = 0;

    switch (excerpt.charAt(i)) {

      case '(' :
        excerpt = excerpt.substring(1, excerpt.length() - 1);
        excerpt = excerpt.trim();
        StringBuilder operator = new StringBuilder("");
        List<Expression> operands = new ArrayList<Expression>();

        // Get Operator
        while (i < excerpt.length() && excerpt.charAt(i) != ' ' && excerpt.charAt(i) != '\t'
                && excerpt.charAt(i) != '\n') {
          operator.append(excerpt.charAt(i));
          i++;
        }

        // Get Operands
        while (i < excerpt.length()) {

          int numParenth = 0;
          boolean isComposite = false;
          StringBuilder curOperand = new StringBuilder("");

          while (i < excerpt.length() && excerpt.charAt(i) == ' ') {
            i++;
          }

          if (excerpt.charAt(i) == '(') {
            numParenth++;
            isComposite = true;
          }
          curOperand.append(excerpt.charAt(i));
          i++;
          while (i < excerpt.length()
                  && (isComposite && numParenth > 0
                  || !isComposite && excerpt.charAt(i) != ' ')) {

            if (excerpt.charAt(i) == '(') {
              numParenth++;
            }
            else if (excerpt.charAt(i) == ')') {
              numParenth--;
            }

            curOperand.append(excerpt.charAt(i));
            i++;
          }

          operands.add(this.getExpression(curOperand.toString()));
        }

        return new Composite(this.parseOperator(operator.toString()), operands);

      default :
        return this.parsePrimitive(excerpt);
    }
  }

  /**
   * Parse an Expression from the input and given token.
   * @param token  the token read from input.
   * @return the Primitive parsed from token.
   */
  private Primitive parsePrimitive(String token) {
    int numerator = 0;
    int denominator = 0;
    int i = 0;

    for (i = 0; i < token.length(); i++) {
      if (token.charAt(i) == '/') {
        break;
      }
      else if (token.charAt(i) >= '0' && token.charAt(i) <= '9') {
        numerator = numerator * 10 + token.charAt(i) - '0';
      }
    }

    if (i == token.length()) {
      denominator = 1;
    }

    for (; i < token.length(); i++) {
      if (token.charAt(i) >= '0' && token.charAt(i) <= '9') {
        denominator = denominator * 10 + token.charAt(i) - '0';
      }
    }

    Primitive ret = null;

    try {
      ret = new Rational(new ImproperFraction(numerator, denominator));
      Objects.requireNonNull(ret);
    }
    catch (ArithmeticError e) {
      e.printStackTrace();
    }
    catch (NullPointerException e) {
      e.printStackTrace();
    }

    return ret;
  }

  /**
   * Parse the operator from given token.
   * @param token  the token read from input.
   * @return the Operator parsed from the input.
   */
  private Operator parseOperator(String token) {

    switch (token) {
      case "+" : return new Add();

      case "-" : return new Subtract();

      case "*" : return new Multiply();

      case "/" : return new Divide();

      default : throw new IllegalStateException("Unsupported Operation: " + token);
    }
  }
}
