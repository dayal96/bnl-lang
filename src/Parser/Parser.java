package Parser;

import Environment.IEnvironment;
import Exceptions.ArithmeticError;
import Expression.Composite;
import Expression.IExpression;
import Expression.Number.ImproperFraction;
import Expression.Number.Rational;
import Expression.Operator.*;
import Expression.Primitive;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Parser for ANL-lang.
 */
public class Parser implements IParser {
    private Readable input;
    private IEnvironment environment;

    /**
     * Creates a new Parser with given {@link Readable} and {@link IEnvironment}.
     * @param input       the {@link Readable} that has the code to parse.
     * @param environment the {@link IEnvironment} that is the global symbol table.
     */
    public Parser(Readable input, IEnvironment environment) {
        this.input = input;
        this.environment = environment;
    }

    @Override
    public List<IExpression> parseCode() {
        List<IExpression> expressions = new ArrayList<>();

        List<String> unparsedExpressions = this.parseExpressionsDisjointly(this.input);

        for (String unparsedExpression : unparsedExpressions) {
            this.parseExpression(unparsedExpression, expressions);
        }

        return expressions;
    }

    /**
     * Parses a single unparsed expression.
     * @param unparsedExpression the unparsed Expression to parse.
     * @return the IExpression representing the parsed expression.
     */
    private void parseExpression(String unparsedExpression, List<IExpression> expressions) {

        if (ParseUtils.isOpenParenth(unparsedExpression.substring(0, 1))) {
            unparsedExpression = unparsedExpression.substring(1, unparsedExpression.length() - 1);
        }

        List<String> componentExpressions = this.parseExpressionsDisjointly(new StringReader(unparsedExpression));

        if (componentExpressions.size() == 1) {
            expressions.add(this.parseToken(componentExpressions.get(0)));
        }
        else if (componentExpressions.get(0).equals("define")) {
            try {
                List<IExpression> rhs = new ArrayList<>();
                this.parseExpression(componentExpressions.get(2), rhs);
                this.environment.addEntry(componentExpressions.get(1), rhs.get(0));
            }
            catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException("define clause needs an identifier and an expression");
            }
        }
        else if (componentExpressions.size() > 1) {
            expressions.add(this.parseComposite(componentExpressions));
        }
        else {
            throw new IllegalStateException("Component expressions had no tokens!");
        }
    }

    /**
     * Parse multiple tokens as a composite expression.
     * @param componentExpressions the List of Tokens/Expressions to parse as a composite expression.
     * @return the Composite Expression parsed as an {@link IExpression}.
     */
    private IExpression parseComposite(List<String> componentExpressions) {
        IOperator operator = this.parseOperator(componentExpressions.get(0));
        List<IExpression> operands = new ArrayList<>();

        for (int i = 1; i < componentExpressions.size(); i++) {
            this.parseExpression(componentExpressions.get(i), operands);
        }

        return new Composite(operator, operands, this.environment);
    }

    /**
     * Parse a token as an Operator.
     * @param token the token to parse as an operator.
     * @return the {@link IOperator} parsed from the token.
     */
    private IOperator parseOperator(String token) {
        if (token.equals("+")) {
            return new Add();
        }
        else if (token.equals("-")) {
            return new Subtract();
        }
        else if (token.equals("*")) {
            return new Multiply();
        }
        else if (token.equals("/")) {
            return new Divide();
        }

        throw new IllegalArgumentException("Operator not found : " + token);
    }

    /**
     * Parse a single token as either a constant in the symbol table or an ANL literal.
     * @param s the String that represents the token to parse.
     * @return the IExpression that reprensents the parsed token.
     */
    private IExpression parseToken(String s) {

        if (ParseUtils.isNumber(s)) {
            return parseNumber(s);
        }
        else if (this.environment.isPresent(s)) {
            return this.environment.getEntry(s);
        }

        throw new IllegalArgumentException("That token was not found.");
    }


    /**
     * Parses all text in given Readable and returns all expressions as a list of Strings.
     * @param source the Readable to parse.
     * @return the List of all Expressions as unparsed Strings.
     */
    private List<String> parseExpressionsDisjointly(Readable source) {
        List<String> allExpressionStrings = new ArrayList<>();
        Scanner scanner = new Scanner(source);
        scanner.useDelimiter("");

        // Parse all code into Strings with only expressions.
        StringBuilder currentExpression = new StringBuilder();
        int numOpen = 0;

        while (scanner.hasNext()) {
            String currentChar = scanner.next();

            if (ParseUtils.isOpenParenth(currentChar)) {
                numOpen++;
                currentExpression.append(currentChar);
            }
            else if (ParseUtils.isCloseParenth(currentChar)) {
                numOpen--;
                currentExpression.append(currentChar);

                if (numOpen == 0) {
                    allExpressionStrings.add(currentExpression.toString().trim());
                    currentExpression = new StringBuilder();
                }
            }
            else if (numOpen == 0 && ParseUtils.isWhitespace(currentChar)) {
                if (currentExpression.toString().trim().length() > 0) {
                    allExpressionStrings.add(currentExpression.toString().trim());
                    currentExpression = new StringBuilder();
                }
            }
            else {
                currentExpression.append(currentChar);
            }
        }

        if (currentExpression.toString().trim().length() > 0) {
            allExpressionStrings.add(currentExpression.toString().trim());
        }

        return allExpressionStrings;
    }

    /**
     * Parse the given token as a Number.
     * @param token  the token read from input.
     * @return the Number parsed from token.
     */
    private static Primitive parseNumber(String token) {
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
}