package io.github.dayal96.primitive.number;

import io.github.dayal96.exceptions.ArithmeticError;
import java.util.Objects;

/**
 * Class to represent a ImproperFraction.
 */
public class ImproperFraction {

  public final int numerator;
  public final int denominator;

  public ImproperFraction(int numerator, int denominator) throws ArithmeticException {
    int gcd = this.gcd(Math.abs(numerator), Math.abs(denominator));
    this.numerator = numerator / gcd;
    this.denominator = denominator / gcd;

    if (this.denominator == 0) {
      throw new ArithmeticException("Rational cannot have a zero as denominator.");
    }
  }

  /**
   * Calculate the GCD of two given numbers.
   *
   * @param a the first number.
   * @param b the second number.
   * @return the greatest common denominator of the two numbers.
   */
  private int gcd(int a, int b) {
    if (b == 0) {
      return a;
    } else {
      return gcd(b, a % b);
    }
  }

  /**
   * Returns String representation of this Fraction.
   *
   * @return the String representation of this Fraction.
   */
  public String toString() {

    if (this.denominator == 1) {
      return String.format("%d", this.numerator);
    } else {
      return String.format("%d/%d", this.numerator, this.denominator);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    } else if (!(other instanceof ImproperFraction)) {
      return false;
    } else {
      ImproperFraction that = (ImproperFraction) other;
      return this.numerator * that.denominator == that.numerator * this.denominator;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.numerator, this.denominator);
  }
}
