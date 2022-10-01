package io.github.dayal96.primitive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import io.github.dayal96.exceptions.ArithmeticError;
import org.junit.Test;
import io.github.dayal96.primitive.number.ImproperFraction;
import io.github.dayal96.primitive.number.MyNumber;
import io.github.dayal96.primitive.number.Rational;

/**
 * Class to test Rational Arithmetic.
 */
public class TestRational extends TestPrimitive {

  public TestRational() throws Exception {
    super(new Rational(0));
  }

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

  @Test
  public void testHashCode() throws Exception {
    Rational first = new Rational(6, 5);
    Rational second = new Rational(5, 7);
    Rational firstCopy = new Rational(6, 5);

    assertEquals(first.hashCode(), first.hashCode());
    assertEquals(second.hashCode(), second.hashCode());
    assertEquals(first.hashCode(), firstCopy.hashCode());
    assertNotEquals(first.hashCode(), second.hashCode());

    assert first.equals(first);
    assert second.equals(second);
    assert first.equals(firstCopy);
    assert firstCopy.equals(first);
    assert !first.equals(second);
    assert !second.equals(first);
  }

  @Test
  public void testImproperFraction() {
    ImproperFraction first = new ImproperFraction(6, 5);
    ImproperFraction second = new ImproperFraction(5, 7);
    ImproperFraction firstCopy = new ImproperFraction(6, 5);

    assertEquals(first.hashCode(), first.hashCode());
    assertEquals(second.hashCode(), second.hashCode());
    assertEquals(first.hashCode(), firstCopy.hashCode());
    assertNotEquals(first.hashCode(), second.hashCode());

    assertEquals("6/5", first.toString());
    assertEquals("5/7", second.toString());
    assertEquals("6/5", firstCopy.toString());

    assert first.equals(first);
    assert second.equals(second);
    assert first.equals(firstCopy);
    assert firstCopy.equals(first);
    assert !first.equals(second);
    assert !second.equals(first);
    assert !second.equals("hola");

    try {
      new ImproperFraction(5, 0);
      assert false;
    } catch (Exception e) {
      assert true;
    }
  }
}
