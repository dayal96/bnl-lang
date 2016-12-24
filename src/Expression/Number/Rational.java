package Expression.Number;

import Exceptions.ArithmeticError;
import Exceptions.DivideByZeroError;

/**
 * Created by amoghlaptop on 16/11/16.
 */
public class Rational extends MyNumber<MixedFraction> {

  /**
   * Creates a new
   * @param number
   */
  public Rational(MixedFraction number) {
    super(number);
  }

  /**
   *
   * @param numerator
   * @param denominator
   * @throws ArithmeticError
   */
  public Rational(int numerator, int denominator) throws ArithmeticError {
    super(new MixedFraction(numerator, denominator));
  }


  @Override
  public MyNumber<MixedFraction> add(MyNumber<MixedFraction> other) throws ArithmeticError {

    int newNumerator = this.number.numerator * other.number.denominator
            + other.number.numerator * this.number.denominator;
    int newDenominator = this.number.denominator * other.number.denominator;

    return new Rational(new MixedFraction(newNumerator, newDenominator));
  }

  @Override
  public MyNumber<MixedFraction> subtract(MyNumber<MixedFraction> other) throws ArithmeticError {
    int newNumerator = this.number.numerator * other.number.denominator
            - other.number.numerator * this.number.denominator;
    int newDenominator = this.number.denominator * other.number.denominator;

    return new Rational(new MixedFraction(newNumerator, newDenominator));
  }

  @Override
  public MyNumber<MixedFraction> multiply(MyNumber<MixedFraction> other) throws ArithmeticError {
    int newNumerator = this.number.numerator * other.number.numerator;
    int newDenominator = this.number.denominator * other.number.denominator;

    return new Rational(new MixedFraction(newNumerator, newDenominator));
  }

  @Override
  public MyNumber<MixedFraction> divide(MyNumber<MixedFraction> other) throws ArithmeticError {

    if (other.number.numerator == 0) {
      throw new DivideByZeroError("Cannot divide a Rational by 0.");
    }

    int newNumerator = this.number.numerator * other.number.denominator;
    int newDenominator = this.number.denominator * other.number.numerator;

    return new Rational(new MixedFraction(newNumerator, newDenominator));
  }
}
