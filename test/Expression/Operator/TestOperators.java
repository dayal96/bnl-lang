package Expression.Operator;

import org.junit.Test;

import Exceptions.ArithmeticError;
import Expression.Expression;
import Expression.Number.MixedFraction;
import Expression.Number.Rational;
import Expression.Value;

import static org.junit.Assert.assertEquals;

/**
 * Class to test Operators.
 */
public class TestOperators {

  @Test
  public void testAdd() throws ArithmeticError {
    Value v1 = new Rational(new MixedFraction(4, 5));
    Value v2 = new Rational(new MixedFraction(4, 5));
    Operator add = new Add();
    Expression exp = new Expression(add, v1, v2);
    assertEquals(new Rational(new MixedFraction(8, 5)), exp.evaluate());

    Expression exp2 = new Expression(add, exp, v2);
    assertEquals(new Rational(new MixedFraction(12, 5)), exp2.evaluate());
  }

  @Test
  public void testMultiply() throws ArithmeticError {
    Value v1 = new Rational(new MixedFraction(4, 5));
    Value v2 = new Rational(new MixedFraction(4, 5));
    Operator multiply = new Multiply();
    Expression exp = new Expression(multiply, v1, v2);
    assertEquals(new Rational(new MixedFraction(16, 25)), exp.evaluate());

    Expression exp2 = new Expression(multiply, exp, v2);
    assertEquals(new Rational(new MixedFraction(64, 125)), exp2.evaluate());
  }
}
