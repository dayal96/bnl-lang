package expression.operator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import environment.SymbolTable;
import exceptions.ArithmeticError;
import expression.Composite;
import expression.IExpression;
import expression.number.ImproperFraction;
import expression.number.Rational;

import static org.junit.Assert.assertEquals;

/**
 * Class to test Operators.
 */
public class TestOperators {

  @Test
  public void testAdd() throws ArithmeticError {
    IExpression v1 = new Rational(new ImproperFraction(4, 5));
    IExpression v2 = new Rational(new ImproperFraction(4, 5));
    IOperator add = new Add();

    List<IExpression> operands1 = new ArrayList<IExpression>();
    operands1.add(v1);
    operands1.add(v2);

    Composite exp = new Composite(add, operands1, new SymbolTable());
    assertEquals(new Rational(new ImproperFraction(8, 5)), exp.evaluate());

    List<IExpression> operands2 = new ArrayList<IExpression>();
    operands2.add(exp);
    operands2.add(v2);

    Composite exp2 = new Composite(add, operands2, new SymbolTable());
    assertEquals(new Rational(new ImproperFraction(12, 5)), exp2.evaluate());
  }

  @Test
  public void testMultiply() throws ArithmeticError {
    IExpression v1 = new Rational(new ImproperFraction(4, 5));
    IExpression v2 = new Rational(new ImproperFraction(4, 5));
    IOperator multiply = new Multiply();

    List<IExpression> operands1 = new ArrayList<IExpression>();
    operands1.add(v1);
    operands1.add(v2);

    Composite exp = new Composite(multiply, operands1, new SymbolTable());
    assertEquals(new Rational(new ImproperFraction(16, 25)), exp.evaluate());

    List<IExpression> operands2 = new ArrayList<IExpression>();
    operands2.add(exp);
    operands2.add(v2);

    Composite exp2 = new Composite(multiply, operands2, new SymbolTable());
    assertEquals(new Rational(new ImproperFraction(64, 125)), exp2.evaluate());
  }
}
