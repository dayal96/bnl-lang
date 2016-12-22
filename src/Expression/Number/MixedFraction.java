package Expression.Number;

import java.util.Objects;

import Exceptions.ArithmeticError;

/**
 * Class to represent a MixedFraction.
 */
public class MixedFraction {
  public final int numerator;
  public final int denominator;

  public MixedFraction(int numerator, int denominator) throws ArithmeticError {
    this.numerator = numerator;
    this.denominator = denominator;

    if (this.denominator == 0) {
      throw new ArithmeticError("Rational cannot have a zero as denominator.");
    }
  }

  /**
   * Returns String representation of this Fraction.
   * @return the String representation of this Fraction.
   */
  public String toString() {
    return String.format("%d/%d", this.numerator, this.denominator);
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    else if (!(other instanceof MixedFraction)) {
      return false;
    }
    else {
      MixedFraction that = (MixedFraction) other;
      return this.numerator == that.numerator && this.denominator == that.denominator;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.numerator, this.denominator);
  }
}
