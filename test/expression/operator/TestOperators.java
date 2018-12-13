package expression.operator;

import expression.FunctionCall;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import environment.SymbolTable;
import exceptions.ArithmeticError;
import expression.IExpression;
import expression.number.ImproperFraction;
import expression.number.Rational;

import static org.junit.Assert.assertEquals;

/**
 * Class to test Operators.
 */
public class TestOperators {

  @Test
  public void testAdd() throws Exception {
    IExpression v1 = new Rational(new ImproperFraction(4, 5));
    IExpression v2 = new Rational(new ImproperFraction(4, 5));
    AOperator add = new Add();

    List<IExpression> operands1 = new ArrayList<>();
    operands1.add(v1);
    operands1.add(v2);

    FunctionCall exp = new FunctionCall(add, operands1);
    assertEquals(new Rational(new ImproperFraction(8, 5)), exp.evaluate(new SymbolTable()));

    List<IExpression> operands2 = new ArrayList<>();
    operands2.add(exp);
    operands2.add(v2);

    FunctionCall exp2 = new FunctionCall(add, operands2);
    assertEquals(new Rational(new ImproperFraction(12, 5)), exp2.evaluate(new SymbolTable()));
  }

  @Test
  public void testMultiply() throws Exception {
    IExpression v1 = new Rational(new ImproperFraction(4, 5));
    IExpression v2 = new Rational(new ImproperFraction(4, 5));
    AOperator multiply = new Multiply();

    List<IExpression> operands1 = new ArrayList<>();
    operands1.add(v1);
    operands1.add(v2);

    FunctionCall exp = new FunctionCall(multiply, operands1);
    assertEquals(new Rational(new ImproperFraction(16, 25)), exp.evaluate(new SymbolTable()));

    List<IExpression> operands2 = new ArrayList<>();
    operands2.add(exp);
    operands2.add(v2);

    FunctionCall exp2 = new FunctionCall(multiply, operands2);
    assertEquals(new Rational(new ImproperFraction(64, 125)), exp2.evaluate(new SymbolTable()));
  }
}
