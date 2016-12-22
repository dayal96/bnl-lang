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
    Number r1 = new Rational(new MixedFraction(3, 5));
    Number r2 = new Rational(new MixedFraction(4, 7));

    Number r3 = r1.add(r2);
    assertEquals(new Rational(new MixedFraction(21 + 20, 35)), r3);

    r1 = new Rational(new MixedFraction(2, 4));
    r2 = new Rational(new MixedFraction(5, 10));
    r3 = r1.add(r2);
    assertEquals(new Rational(new MixedFraction(1, 1)), r3);

    r1 = new Rational(new MixedFraction(-2, 4));
    r2 = new Rational(new MixedFraction(5, 10));
    r3 = r1.add(r2);
    assertEquals(new Rational(new MixedFraction(0, 1)), r3);
  }

  @Test
  public void testSubtract() throws ArithmeticError {
    Number r1 = new Rational(new MixedFraction(3, 5));
    Number r2 = new Rational(new MixedFraction(4, 7));

    Number r3 = r1.subtract(r2);
    assertEquals(new Rational(new MixedFraction(1, 35)), r3);

    r1 = new Rational(new MixedFraction(2, 4));
    r2 = new Rational(new MixedFraction(5, 10));
    r3 = r1.subtract(r2);
    assertEquals(new Rational(new MixedFraction(0, 1)), r3);

    r1 = new Rational(new MixedFraction(-2, 4));
    r2 = new Rational(new MixedFraction(5, 10));
    r3 = r1.subtract(r2);
    assertEquals(new Rational(new MixedFraction(-1, 1)), r3);
  }

  @Test
  public void testMultiply() throws ArithmeticError {
    Number r1 = new Rational(new MixedFraction(3, 5));
    Number r2 = new Rational(new MixedFraction(4, 7));

    Number r3 = r1.multiply(r2);
    assertEquals(new Rational(new MixedFraction(12, 35)), r3);

    r1 = new Rational(new MixedFraction(2, 4));
    r2 = new Rational(new MixedFraction(5, 10));
    r3 = r1.multiply(r2);
    assertEquals(new Rational(new MixedFraction(1, 4)), r3);

    r1 = new Rational(new MixedFraction(-2, 4));
    r2 = new Rational(new MixedFraction(5, 10));
    r3 = r1.multiply(r2);
    assertEquals(new Rational(new MixedFraction(-1, 4)), r3);

    r1 = new Rational(new MixedFraction(-2, 4));
    r2 = new Rational(new MixedFraction(-5, 10));
    r3 = r1.multiply(r2);
    assertEquals(new Rational(new MixedFraction(1, 4)), r3);
  }

  @Test
  public void testDivide() throws ArithmeticError {

    Number r1 = new Rational(new MixedFraction(3, 5));
    Number r2 = new Rational(new MixedFraction(4, 7));

    Number r3 = r1.divide(r2);
    assertEquals(new Rational(new MixedFraction(21, 20)), r3);

    r1 = new Rational(new MixedFraction(2, 4));
    r2 = new Rational(new MixedFraction(5, 10));
    r3 = r1.divide(r2);
    assertEquals(new Rational(new MixedFraction(1, 1)), r3);

    r1 = new Rational(new MixedFraction(-2, 4));
    r2 = new Rational(new MixedFraction(5, 10));
    r3 = r1.divide(r2);
    assertEquals(new Rational(new MixedFraction(-1, 1)), r3);

    r1 = new Rational(new MixedFraction(-2, 4));
    r2 = new Rational(new MixedFraction(-5, 10));
    r3 = r1.divide(r2);
    assertEquals(new Rational(new MixedFraction(1, 1)), r3);
  }
}
