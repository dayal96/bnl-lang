package Parser;

import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import Environment.SymbolTable;
import Exceptions.ArithmeticError;
import Expression.IExpression;
import Expression.Composite;
import Expression.Number.Rational;
import Expression.Operator.Add;
import Expression.Operator.Divide;
import Expression.Operator.Multiply;
import Expression.Operator.Subtract;

import static org.junit.Assert.assertEquals;

/**
 * Class to test Parser.
 */
public class TestParser {

  @Test
  public void testParseSimple() throws ArithmeticError {
    String input = "(+ 1 2)";
    IParser parser = new Parser(new StringReader(input), new SymbolTable());
    List<IExpression> expressions = parser.parseCode();

    List<IExpression> operands = new ArrayList<IExpression>();
    operands.add(new Rational(1, 1));
    operands.add(new Rational(2, 1));

    assertEquals(1, expressions.size());
    assertEquals(new Composite(new Add(), operands, new SymbolTable()),
        expressions.get(0));

    input = "(+ 1 2 )";
    parser = new Parser(new StringReader(input), new SymbolTable());
    expressions = parser.parseCode();

    operands = new ArrayList<IExpression>();
    operands.add(new Rational(1, 1));
    operands.add(new Rational(2, 1));

    assertEquals(1, expressions.size());
    assertEquals(new Composite(new Add(), operands, new SymbolTable()),
        expressions.get(0));
  }

  @Test
  public void testParseComplex1() throws ArithmeticError {
    String input = "(+ (* 1 5) (* 2 (+ 7 9)))";
    //                  EXP1    EXP2 EXP3
    // EXP3 is inside EXP2
    IParser parser = new Parser(new StringReader(input), new SymbolTable());
    List<IExpression> expressions = parser.parseCode();

    List<IExpression> operands1 = new ArrayList<IExpression>();
    List<IExpression> operands2 = new ArrayList<IExpression>();
    List<IExpression> operands3 = new ArrayList<IExpression>();
    List<IExpression> operandsToParse = new ArrayList<IExpression>();

    operands3.add(new Rational(7, 1));
    operands3.add(new Rational(9, 1));
    IExpression exp3 = new Composite(new Add(), operands3, new SymbolTable());

    operands2.add(new Rational(2, 1));
    operands2.add(exp3);
    IExpression exp2 = new Composite(new Multiply(), operands2, new SymbolTable());

    operands1.add(new Rational(1, 1));
    operands1.add(new Rational(5, 1));
    IExpression exp1 = new Composite(new Multiply(), operands1, new SymbolTable());

    operandsToParse.add(exp1);
    operandsToParse.add(exp2);
    IExpression toParse = new Composite(new Add(), operandsToParse, new SymbolTable());

    assertEquals(1, expressions.size());
    assertEquals(toParse, expressions.get(0));
  }

  @Test
  public void testParseComplex2() throws ArithmeticError {
    String input = "(+ (* 1 5) (* 2 (/ 7 9))) (- 1 2)";
    IParser parser = new Parser(new StringReader(input), new SymbolTable());
    List<IExpression> expressions = parser.parseCode();

    List<IExpression> operands1 = new ArrayList<IExpression>();
    List<IExpression> operands2 = new ArrayList<IExpression>();
    List<IExpression> operands3 = new ArrayList<IExpression>();
    List<IExpression> operandsToParse1 = new ArrayList<IExpression>();
    List<IExpression> operandsToParse2 = new ArrayList<IExpression>();

    operands3.add(new Rational(7, 1));
    operands3.add(new Rational(9, 1));
    IExpression exp3 = new Composite(new Divide(), operands3, new SymbolTable());

    operands2.add(new Rational(2, 1));
    operands2.add(exp3);
    IExpression exp2 = new Composite(new Multiply(), operands2, new SymbolTable());

    operands1.add(new Rational(1, 1));
    operands1.add(new Rational(5, 1));
    IExpression exp1 = new Composite(new Multiply(), operands1, new SymbolTable());

    operandsToParse1.add(exp1);
    operandsToParse1.add(exp2);
    IExpression toParse1 = new Composite(new Add(), operandsToParse1, new SymbolTable());

    operandsToParse2.add(new Rational(1, 1));
    operandsToParse2.add(new Rational(2, 1));
    IExpression toParse2 = new Composite(new Subtract(), operandsToParse2, new SymbolTable());

    assertEquals(2, expressions.size());
    assertEquals(toParse1, expressions.get(0));
    assertEquals(toParse2, expressions.get(1));
  }

  @Test
  public void testParseDefinitions() throws ArithmeticError {
    String input = "(define TWO 2)";
    IParser parser = new Parser(new StringReader(input), new SymbolTable());
    List<IExpression> expressions = parser.parseCode();

    assertEquals(0, expressions.size());

    input = "(define TWO 2)\n"
        + "(define FIVE 5)\n"
        + "TWO FIVE (+ TWO FIVE)";
    parser = new Parser(new StringReader(input), new SymbolTable());
    expressions = parser.parseCode();

    assertEquals(3, expressions.size());
    assertEquals(new Rational(2, 1), expressions.get(0).evaluate());
    assertEquals(new Rational(5, 1), expressions.get(1).evaluate());
    assertEquals(new Rational(7, 1), expressions.get(2).evaluate());
  }

  @Test
  public void testParseNoInput() throws ArithmeticError {
    String input = "";
    IParser parser = new Parser(new StringReader(input), new SymbolTable());
    List<IExpression> expressions = parser.parseCode();
    assertEquals(0, expressions.size());
  }
}
