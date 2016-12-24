package Expression.Operator;

import org.junit.Test;

import Exceptions.ArithmeticError;
import Expression.Composite;
import Expression.Number.MixedFraction;
import Expression.Number.Rational;
import Expression.Expression;

import static org.junit.Assert.assertEquals;

/**
 * Class to test Operators.
 */
public class TestOperators {

  @Test
  public void testAdd() throws ArithmeticError {
    Expression v1 = new Rational(new MixedFraction(4, 5));
    Expression v2 = new Rational(new MixedFraction(4, 5));
    Operator add = new Add();
    Composite exp = new Composite(add, v1, v2);
    assertEquals(new Rational(new MixedFraction(8, 5)), exp.evaluate());

    Composite exp2 = new Composite(add, exp, v2);
    assertEquals(new Rational(new MixedFraction(12, 5)), exp2.evaluate());
  }

  @Test
  public void testMultiply() throws ArithmeticError {
    Expression v1 = new Rational(new MixedFraction(4, 5));
    Expression v2 = new Rational(new MixedFraction(4, 5));
    Operator multiply = new Multiply();
    Composite exp = new Composite(multiply, v1, v2);
    assertEquals(new Rational(new MixedFraction(16, 25)), exp.evaluate());

    Composite exp2 = new Composite(multiply, exp, v2);
    assertEquals(new Rational(new MixedFraction(64, 125)), exp2.evaluate());
  }
}
