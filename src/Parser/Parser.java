package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import Environment.IEnvironment;
import Exceptions.ArithmeticError;
import Expression.IExpression;
import Expression.Composite;
import Expression.Number.ImproperFraction;
import Expression.Number.Rational;
import Expression.Operator.Add;
import Expression.Operator.Divide;
import Expression.Operator.IOperator;
import Expression.Operator.Multiply;
import Expression.Operator.Subtract;
import Expression.Primitive;

/**
 * Class to represent a Parser for anl-lang.
 */
public class Parser {

  private Scanner scan;
  private IEnvironment environment;

  /**
   * Creates a Parser that reads from given Readable.
   * @param input the readable from which to read code.
   */
  public Parser(Readable input, IEnvironment environment) {

    String wasNull = "Input Stream";

    try {
      Objects.requireNonNull(input);
      wasNull = "Environment";
      Objects.requireNonNull(environment);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException(wasNull + " provided was null.");
    }

    this.environment = environment;
    this.scan = new Scanner(input);
    this.scan.useDelimiter("");
  }

  /**
   * Parse the code to get a list of expressions.
   * @return the list of all expressions in input stream.
   */
  public List<IExpression> parseCode() {
    List<IExpression> expressions = new ArrayList<IExpression>();

    while (this.scan.hasNext()) {
      String excerpt = this.getExcerpt();
      this.parseExcerpt(excerpt, expressions);
    }

    return expressions;
  }

  /**
   * Parse an excerpt from input.
   * @param excerpt      the excerpt to parse.
   * @param expressions  the expressions parsed from input so far.
   */
  private void parseExcerpt(String excerpt, List<IExpression> expressions) {
    List<String> tokens = this.getTokens(excerpt);

    if (tokens.get(0).equals("define")) {
      if (tokens.size() != 3) {
        throw new IllegalArgumentException("Definitions must have one identifier and "
                + "one expression.");
      }

      this.environment.addEntry(tokens.get(1),
              this.getExpression(this.getTokens(tokens.get(2))));
    }
    else {
      expressions.add(this.getExpression(tokens));
    }
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
   * @param tokens  the tokens to be parsed.
   * @return a single IExpression parsed from the input.
   */
  private IExpression getExpression(List<String> tokens) {

    if (tokens.size() == 0) {
      throw new IllegalArgumentException("No tokens detected in " + tokens);
    }
    else if (tokens.size() == 1) {
      return this.parseToken(tokens.get(0));
    }
    else if (this.isOperator(tokens.get(0))) {
      IOperator operator = this.parseOperator(tokens.get(0));
      List<IExpression> operands = new ArrayList<IExpression>();

      for (int i = 1; i < tokens.size(); i++) {
        operands.add(this.getExpression(this.getTokens(tokens.get(i))));
      }

      return new Composite(operator, operands, this.environment);
    }

    throw new IllegalArgumentException("Invalid Tokens from input: " + tokens);
  }

  /**
   * Is the given token an Operator?
   * @param token  the token read from input.
   * @return true if the given token is an operator, false otherwise.
   */
  private boolean isOperator(String token) {
    return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
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
   * Parse the given token as a Primitive or Symbol.
   * @param token  the token read from input to parse.
   * @return the IExpression associated with the Symbol.
   */
  private IExpression parseToken(String token) {
    if (this.isNumber(token)) {
      return this.parseNumber(token);
    } else {
      return this.parseSymbol(token);
    }
  }

  /**
   * Parse a token as a symbol using the Environment definitions.
   * @param token  the symbol to parse.
   * @return the value associated with the symbol in the Environment.
   */
  private IExpression parseSymbol(String token) {

    if (!this.environment.isPresent(token)) {
      throw new IllegalArgumentException("Token not found: " + token);
    }
    return this.environment.getEntry(token);
  }

  /**
   * Is the given token a Number?
   * @param token  the token read from input.
   * @return true if the given token is a Number, false otherwise.
   */
  private boolean isNumber(String token) {

    for (int i = 0; i < token.length(); i++) {
      if ((token.charAt(i) > '9' || token.charAt(i) < '0') && token.charAt(i) != '/') {
        return false;
      }
    }

    return token.charAt(0) != '/' && token.charAt(token.length() - 1) != '/';
  }

  /**
   * Parse the given token as a Number.
   * @param token  the token read from input.
   * @return the Number parsed from token.
   */
  private Primitive parseNumber(String token) {
    int numerator = 0;
    int denominator = 0;
    int i;

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
   * @return the IOperator parsed from the input.
   */
  private IOperator parseOperator(String token) {

    switch (token) {
      case "+" : return new Add();

      case "-" : return new Subtract();

      case "*" : return new Multiply();

      case "/" : return new Divide();

      default : throw new IllegalStateException("Unsupported Operation: " + token);
    }
  }
}
