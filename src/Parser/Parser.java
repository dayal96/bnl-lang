package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import Exceptions.ArithmeticError;
import Expression.Expression;
import Expression.Composite;
import Expression.Number.MixedFraction;
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

  Scanner scan;

  /**
   * Creates a Parser that reads from given Readable.
   * @param input the readable from which to read code.
   */
  public Parser(Readable input) {
    this.scan = new Scanner(input);
  }

  /**
   * Parse the code to get a list of expressions.
   * @return the list of all expressions in input stream.
   */
  public List<Expression> parseCode() {
    List<Expression> expressions = new ArrayList<Expression>();

    while (scan.hasNext()) {
      expressions.add(this.getExpression());
    }

    return expressions;
  }

  /**
   * Parse the code to get a single expression from the code.
   * @return a single Expression parsed from the input.
   */
  private Expression getExpression() {
    while (scan.hasNext()) {
      String token = scan.next();

      switch (token.charAt(0)) {

        case '(':
          return new Composite(this.parseOperator(token), this.getExpression(),
                  this.getExpression());

        default:
          return this.parsePrimitive(token);
      }
    }

    throw new IllegalStateException("No input provided");
  }

  /**
   * Parse an Expression from the input and given token.
   * @param token  the token already read from input.
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
      ret = new Rational(new MixedFraction(numerator, denominator));
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
   * @param token  the token from input.
   * @return the Operator parsed from the input.
   */
  private Operator parseOperator(String token) {
    token = token.substring(1);

    switch (token) {
      case "+" : return new Add();

      case "-" : return new Subtract();

      case "*" : return new Multiply();

      case "/" : return new Divide();

      default : throw new IllegalStateException("Unsupported Operation.");
    }
  }
}
