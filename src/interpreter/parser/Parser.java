package interpreter.parser;

import expression.IExpression;
import expression.Variable;
import expression.bool.MyBoolean;
import expression.lambda.FunctionCall;
import expression.lambda.Lambda;
import expression.number.MyNumber;
import expression.number.Rational;
import interpreter.Definition;
import interpreter.EvaluableExpression;
import interpreter.IEvaluable;

import java.io.StringReader;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Parser implements IParser {

  private static final List reserved = List.of("define");
  private final Collection<String> primitiveOperators;

  /**
   * Create a Parser configured with given primitive operations that the language supports.
   * @param primitiveOperators  The primitive operators that are reserved by the language.
   */
  public Parser(Collection<String> primitiveOperators) {
    this.primitiveOperators = primitiveOperators;
  }

  @Override
  public List<IEvaluable> parseEvaluables(Readable input) throws Exception {

    Scanner inputReader = new Scanner(input);
    inputReader.useDelimiter("");
    List<IEvaluable> toEval = new LinkedList<>();

    while (inputReader.hasNext()) {
      toEval.add(parseNextEvaluable(getNextParseElement(inputReader)));
    }

    return toEval;
  }

  /**
   * Parser the next expression and return as an evaluable expression for the evaluator.
   * @param toParse     The parse element to parse.
   * @return An IEvaluable that can evaluate the expression.
   * @throws Exception  If an expression is not a well-formed expression.
   */
  private IEvaluable parseNextEvaluable(ParseElement toParse)
      throws Exception {

    if (toParse.isCompound()) {
      return parseCompoundEvaluable(toParse.content);
    }
    else {
      return new EvaluableExpression(parsePrimitiveExpression(toParse.content));
    }
  }

  /**
   * Parse a Compound Expression as an evaluable expression for the evaluator.
   * @param toParse   The String that represents a compound expression, no parenthesis at beginning or end.
   * @return An IEvaluable that can evaluate the expression.
   * @throws Exception  If the expression is not well-formed.
   */
  private IEvaluable parseCompoundEvaluable(String toParse) throws Exception {

    Scanner expressionScanner = new Scanner(new StringReader(toParse.trim()));
    expressionScanner.useDelimiter("");

    ParseElement firstExpression = getNextParseElement(expressionScanner);

    // If the first expression is the define token, then this is a Definition evaluable.
    if (firstExpression.isPrimitive() && firstExpression.content.equals("define")) {
      String name = getNextParseElement(expressionScanner).content;

      if (isReserved(name)) {
        throw new Exception(name + " is already defined.");
      }

      IExpression definition = parseExpression(getNextParseElement(expressionScanner));
      return new Definition(name, definition);
    }

    // Otherwise it is a regular IExpression, so we delegate to the function that parses those.
    return new EvaluableExpression(parseCompoundExpression(toParse));
  }

  /**
   * Parse a Parse Element as an Expression.
   * @param toParse The parse element to parse.
   * @return  The IExpression represented by the parse element.
   * @throws Exception  If the Parse Element is not well formed.
   */
  private IExpression parseExpression(ParseElement toParse) throws Exception {
    if (toParse.isCompound()) {
      return parseCompoundExpression(toParse.content);
    }
    else {
      return parsePrimitiveExpression(toParse.content);
    }
  }

  /**
   * Parse the given String as an IExpression.
   * @param toParse The given String to parse.
   * @return  The IExpression represented by the String.
   * @throws Exception If the expression is not well-formed.
   */
  private IExpression parseCompoundExpression(String toParse) throws Exception {
    Scanner expressionScanner = new Scanner(new StringReader(toParse));
    expressionScanner.useDelimiter("");

    ParseElement firstEle = getNextParseElement(expressionScanner);

    // Check if it is a lambda expression.
    if (isLambda(firstEle.content)) {
      // The next compound expression will be an input list.
      ParseElement inputListPE = getNextParseElement(expressionScanner);
      Scanner tokenReader = new Scanner(inputListPE.content);
      tokenReader.useDelimiter("");
      List<String> inputList = new LinkedList<>();
      while (tokenReader.hasNext()) {
        inputList.add(getToken(tokenReader));
      }

      // The next expression will be the function body.
      IExpression body = parseExpression(getNextParseElement(expressionScanner));

      return new Lambda(inputList, body);
    }
    // Otherwise it is a function call, since those are the only possible compound expressions.
    else {
      IExpression operator = parseExpression(firstEle);
      List<IExpression> operands = new LinkedList<>();

      while (expressionScanner.hasNext()) {
        ParseElement pe = getNextParseElement(expressionScanner);
        operands.add(parseExpression(pe));
      }

      return new FunctionCall(operator, operands);
    }
  }

  /**
   * Parse a primitive expression.
   * @param toParse The expression to parse into a primitive.
   * @return The IExpression that is represented by the String provided.
   * @throws Exception  If the expression is not a valid primitive.
   */
  private IExpression parsePrimitiveExpression(String toParse)
      throws Exception {

    if (isNumber(toParse)) {
      return parseNumber(toParse);
    }
    else if (isBoolean(toParse)) {
      return parseBoolean(toParse);
    }
    else {
      return new Variable(toParse);
    }
  }

  /**
   * Is the given character Whitespace?
   * @param ch The character to check.
   * @return  True if it is a space, tab or newline, false otherwise.
   */
  private boolean isWhitespace(char ch) {
    return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r';
  }

  /**
   * Is the given character a numeric digit?
   * @param ch  The character to check.
   * @return  True if it is a numerical digit, false otherwise.
   */
  private boolean isDigit(char ch) {
    return ch <= '9' && ch >= '0';
  }

  /**
   * Is the given string a valid number representation?
   * @param expression  THe string to check.
   * @return  True if it is a valid number expression, false otherwise.
   */
  private boolean isNumber(String expression) {

    if (expression.length() == 0) {
      return false;
    }

    int i = 0;

    if (expression.charAt(i) == '-') {
      i++;
    }

    if (i >= expression.length()) {
      return false;
    }

    boolean isNumber = isDigit(expression.charAt(i));
    boolean slashFound = false;
    i++;

    for (; i < expression.length(); i++) {

      isNumber = isNumber &
          (isDigit(expression.charAt(i)) || (!slashFound & expression.charAt(i) == '/'));
      slashFound = slashFound || expression.charAt(i) == '/';
    }

    return isNumber;
  }

  /**
   * Is the given String an expression for a boolean?
   * @param expression  The string to check.
   * @return  True if it is a boolean, false otherwise.
   */
  private boolean isBoolean(String expression) {
    return expression.equals("#t") || expression.equals("#f");
  }

  /**
   * Is the given string a lambda token?
   * @param toCheck The String to check.
   * @return  True if it is a Lambda token, false otherwise.
   */
  private boolean isLambda(String toCheck) {
    return toCheck.equals("lambda") || toCheck.equals("λ");
  }

  /**
   * Parse a String to determine the Number it represents.
   * @param expression  The String to parse as a number.
   * @return The IExpression that is the Number represented by the String.
   */
  private MyNumber parseNumber(String expression) throws Exception {
    int numerator = 0;
    int denominator = 1;
    int multiple = 1;

    boolean denom = false;
    int i = 0;

    if (expression.charAt(i) == '-') {
      multiple *= -1;
      i++;
    }

    for (; i < expression.length(); i++) {

      if (expression.charAt(i) == '/') {
        denom = true;
        denominator = 0;

        if (expression.charAt(i + 1) == '-') {
          multiple *= -1;
          i++;
        }
      }
      else if (!denom) {
        numerator = numerator * 10 + expression.charAt(i) - '0';
      }
      else {
        denominator = denominator * 10 + expression.charAt(i) - '0';
      }
    }

    return new Rational(multiple * numerator, denominator);
  }

  /**
   * Parse a String to determine the Boolean it represents.
   * @param expression  The String to parse as a Boolean.
   * @return  The MyBoolean represented by the String.
   */
  private MyBoolean parseBoolean(String expression) throws Exception {
    if (expression.equals("#t")) {
      return MyBoolean.TRUE;
    }
    else if (expression.equals("#f")) {
      return MyBoolean.FALSE;
    }

    throw new Exception(expression + " is not a Boolean.");
  }

  /**
   * Is the given String a reserved token?
   * @param name  The given String.
   * @return  True if it is a reserved token, false otherwise.
   */
  private boolean isReserved(String name) {
    return Parser.reserved.contains(name) || this.primitiveOperators.contains(name);
  }

  static class ParseElement {
    public final boolean primitive;
    public final String content;

    /**
     * Creates a ParseElement with the String that represents the element and a flag indicating if its a boolean or not.
     * @param primitive Flag that indicates whether this element is primitive or not.
     * @param content   The String content of this ParseElement.
     */
    private ParseElement(boolean primitive, String content) {
      this.primitive = primitive;
      this.content = content;
    }

    public static ParseElement createPrimitive(String content) {
      return new ParseElement(true, content);
    }

    public static ParseElement createCompound(String content) {
      return new ParseElement(false, content);
    }

    public boolean isPrimitive() {
      return this.primitive;
    }

    public boolean isCompound() {
      return !this.primitive;
    }
  }

  /**
   * Returns the next Expression, complex or otherwise from scanner that reads the input.
   * @param inputReader The scanner that reads the input.
   * @return  The ParseElement representing one complete expression, compound or token.
   */
  private ParseElement getNextParseElement(Scanner inputReader) {
    char current = inputReader.next().charAt(0);

    while (isWhitespace(current) && inputReader.hasNext()) {
      current = inputReader.next().charAt(0);
    }

    if (current == '(') {
      return ParseElement.createCompound(getCompoundToken(inputReader));
    }
    else {

      if (inputReader.hasNext()) {
        return ParseElement.createPrimitive(current + getToken(inputReader));
      }
      else {
        return ParseElement.createPrimitive(current + "");
      }
    }
  }

  /**
   * Parse and get a compound expression from the input scanner.
   * @param inputReader The scanner than reads the input.
   * @return  The String that represents a compound expression, with first and last parenths removed.
   */
  private String getCompoundToken(Scanner inputReader) {
    int numParens = 1;
    StringBuilder expression = new StringBuilder();

    while (numParens > 0 && inputReader.hasNext()) {
      char current = inputReader.next().charAt(0);
      if (current == '(') {
        numParens++;
        expression.append(current);
      }
      else if (current == ')') {
        numParens--;
        if (numParens > 0) {
          expression.append(current);
        }
      }
      else {
        expression.append(current);
      }
    }

    return expression.toString().trim();
  }

  /**
   * Get the next token from the input.
   * @param inputReader The scanner that is reading the input.
   * @return  The String that represents the next token.
   */
  private String getToken(Scanner inputReader) {
    StringBuilder token = new StringBuilder();

    char current;

    do {
      current = inputReader.next().charAt(0);
      if (!isWhitespace(current)) {
        token.append(current);
      }
    } while (!isWhitespace(current) && inputReader.hasNext());

    return token.toString();
  }
}
