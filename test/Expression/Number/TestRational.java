package Expression.Number;

import org.junit.Test;

import Exceptions.ArithmeticError;

import static org.junit.Assert.assertEquals;

/**
 * Class to test Rational Arithmetic.
 */
public class TestRational {

  @Test
  public void testAdd() throws ArithmeticError {
    Rational r1 = new Rational(new MixedFraction(3, 5));
    Rational r2 = new Rational(new MixedFraction(4, 7));

    Number r3 = r1.add(r2);
    assertEquals(new Rational(21 + 20, 35), r3);
  }
}
