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
    while (this.scan.hasNext() && this.isDelim(c.charAt(0))) {
      c = this.scan.next();
    }

    if (c.equals("(")) {
      numParenth++;
      isComposite = true;
    }
    excerpt.append(c);
    while (this.scan.hasNext()
            && (isComposite && numParenth > 0
            || !isComposite && !this.isDelim(c.charAt(0)))) {
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
    List<String> tokens = this.getTokens(excerpt);

    if (tokens.size() == 1) {
      return this.parsePrimitive(tokens.get(0));
    }
    else if (tokens.size() > 1) {
      Operator operator = this.parseOperator(tokens.get(0));
      List<Expression> operands = new ArrayList<Expression>();

      for (int i = 1; i < tokens.size(); i++) {
        operands.add(this.getExpression(tokens.get(i)));
      }

      return new Composite(operator, operands);
    }
    else {
      throw new IllegalArgumentException("No tokens detected in \"" + excerpt + "\"");
    }
  }

  /**
   * Retrieve all tokens from an excerpt.
   * @param excerpt  the excerpt from input.
   * @return the list of all tokens, in the order they appear in the excerpt.
   */
  private List<String> getTokens(String excerpt) {

    // Remove any garbage spaces.
    excerpt = excerpt.trim();

    if (excerpt.length() == 0) {
      return new ArrayList<String>();
    }
    else if (excerpt.charAt(0) == '(') {
      excerpt = excerpt.substring(1, excerpt.length() - 1);
    }

    // Add a delimiter to the end, just a formatting trick.
    excerpt = excerpt + " ";
    List<String> tokens = new ArrayList<String>();
    StringBuilder curToken = new StringBuilder();
    int numParenth = 0;
    boolean isComposite = false;

    for (int i = 0; i < excerpt.length(); i++) {
      if (this.isDelim(excerpt.charAt(i))
              && (!isComposite || (isComposite && numParenth == 0))) {

        if (curToken.toString().trim().equals("")) {
          continue;
        }

        isComposite = false;
        tokens.add(curToken.toString().trim());
        curToken = new StringBuilder();

      } else {
        curToken.append(excerpt.charAt(i));
        switch (excerpt.charAt(i)) {
          case '(':
            numParenth++;
            isComposite = true;
            break;

          case ')':
            numParenth--;
            break;
        }
      }
    }

    return tokens;
  }

  /**
   * Is given character a delimiter?
   * @param ch  the given character.
   * @return true if the given character is a ' ', '\t' or '\n', false otherwise.
   */
  private boolean isDelim(char ch) {
    return ch == ' ' || ch == '\t' || ch == '\n';
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
