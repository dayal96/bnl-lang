package io.github.dayal96.number;

import static org.junit.Assert.assertEquals;

import io.github.dayal96.exceptions.ArithmeticError;
import org.junit.Test;
import io.github.dayal96.primitive.number.ImproperFraction;
import io.github.dayal96.primitive.number.MyNumber;
import io.github.dayal96.primitive.number.Rational;

/**
 * Class to test Rational Arithmetic.
 */
public class TestRational {

  @Test
  public void testAdd() throws ArithmeticError {
    MyNumber r1 = new Rational(new ImproperFraction(3, 5));
    MyNumber r2 = new Rational(new ImproperFraction(4, 7));

    MyNumber r3 = r1.add(r2);
    assertEquals(new Rational(new ImproperFraction(21 + 20, 35)), r3);

    r1 = new Rational(new ImproperFraction(2, 4));
    r2 = new Rational(new ImproperFraction(5, 10));
    r3 = r1.add(r2);
    assertEquals(new Rational(new ImproperFraction(1, 1)), r3);

    r1 = new Rational(new ImproperFraction(-2, 4));
    r2 = new Rational(new ImproperFraction(5, 10));
    r3 = r1.add(r2);
    assertEquals(new Rational(new ImproperFraction(0, 1)), r3);
  }

  @Test
  public void testSubtract() throws ArithmeticError {
    MyNumber r1 = new Rational(new ImproperFraction(3, 5));
    MyNumber r2 = new Rational(new ImproperFraction(4, 7));

    MyNumber r3 = r1.subtract(r2);
    assertEquals(new Rational(new ImproperFraction(1, 35)), r3);

    r1 = new Rational(new ImproperFraction(2, 4));
    r2 = new Rational(new ImproperFraction(5, 10));
    r3 = r1.subtract(r2);
    assertEquals(new Rational(new ImproperFraction(0, 1)), r3);

    r1 = new Rational(new ImproperFraction(-2, 4));
    r2 = new Rational(new ImproperFraction(5, 10));
    r3 = r1.subtract(r2);
    assertEquals(new Rational(new ImproperFraction(-1, 1)), r3);
  }

  @Test
  public void testMultiply() throws ArithmeticError {
    MyNumber r1 = new Rational(new ImproperFraction(3, 5));
    MyNumber r2 = new Rational(new ImproperFraction(4, 7));

    MyNumber r3 = r1.multiply(r2);
    assertEquals(new Rational(new ImproperFraction(12, 35)), r3);

    r1 = new Rational(new ImproperFraction(2, 4));
    r2 = new Rational(new ImproperFraction(5, 10));
    r3 = r1.multiply(r2);
    assertEquals(new Rational(new ImproperFraction(1, 4)), r3);

    r1 = new Rational(new ImproperFraction(-2, 4));
    r2 = new Rational(new ImproperFraction(5, 10));
    r3 = r1.multiply(r2);
    assertEquals(new Rational(new ImproperFraction(-1, 4)), r3);

    r1 = new Rational(new ImproperFraction(-2, 4));
    r2 = new Rational(new ImproperFraction(-5, 10));
    r3 = r1.multiply(r2);
    assertEquals(new Rational(new ImproperFraction(1, 4)), r3);
  }

  @Test
  public void testDivide() throws ArithmeticError {

    MyNumber r1 = new Rational(new ImproperFraction(3, 5));
    MyNumber r2 = new Rational(new ImproperFraction(4, 7));

    MyNumber r3 = r1.divide(r2);
    assertEquals(new Rational(new ImproperFraction(21, 20)), r3);

    r1 = new Rational(new ImproperFraction(2, 4));
    r2 = new Rational(new ImproperFraction(5, 10));
    r3 = r1.divide(r2);
    assertEquals(new Rational(new ImproperFraction(1, 1)), r3);

    r1 = new Rational(new ImproperFraction(-2, 4));
    r2 = new Rational(new ImproperFraction(5, 10));
    r3 = r1.divide(r2);
    assertEquals(new Rational(new ImproperFraction(-1, 1)), r3);

    r1 = new Rational(new ImproperFraction(-2, 4));
    r2 = new Rational(new ImproperFraction(-5, 10));
    r3 = r1.divide(r2);
    assertEquals(new Rational(new ImproperFraction(1, 1)), r3);
  }
}
