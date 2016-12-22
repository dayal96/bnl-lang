package Expression.Number;

import Exceptions.ArithmeticError;
import Exceptions.DivideByZeroError;

/**
 * Created by amoghlaptop on 16/11/16.
 */
public class Rational extends Number<MixedFraction> {

  public Rational(MixedFraction number) {
    super(number);
  }

  @Override
  public Number<MixedFraction> add(Number<MixedFraction> other) throws ArithmeticError {

    int newNumerator = this.number.numerator * other.number.denominator
            + other.number.numerator * this.number.denominator;
    int newDenominator = this.number.denominator * other.number.denominator;

    return new Rational(new MixedFraction(newNumerator, newDenominator));
  }

  @Override
  public Number<MixedFraction> subtract(Number<MixedFraction> other) throws ArithmeticError {
    int newNumerator = this.number.numerator * other.number.denominator
            - other.number.numerator * this.number.denominator;
    int newDenominator = this.number.denominator * other.number.denominator;

    return new Rational(new MixedFraction(newNumerator, newDenominator));
  }

  @Override
  public Number<MixedFraction> multiply(Number<MixedFraction> other) throws ArithmeticError {
    int newNumerator = this.number.numerator * other.number.numerator;
    int newDenominator = this.number.denominator * other.number.denominator;

    return new Rational(new MixedFraction(newNumerator, newDenominator));
  }

  @Override
  public Number<MixedFraction> divide(Number<MixedFraction> other) throws ArithmeticError {

    if (other.number.numerator == 0) {
      throw new DivideByZeroError("Cannot divide a Rational by 0.");
    }

    int newNumerator = this.number.numerator * other.number.denominator;
    int newDenominator = this.number.denominator * other.number.numerator;

    return new Rational(new MixedFraction(newNumerator, newDenominator));
  }
}
