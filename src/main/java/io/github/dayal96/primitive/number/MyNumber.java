package io.github.dayal96.primitive.number;

import io.github.dayal96.exceptions.ArithmeticError;
import io.github.dayal96.exceptions.DivideByZeroError;
import io.github.dayal96.primitive.Primitive;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.PrimType;
import java.util.Objects;

/**
 * Interface to represent a number in a given type. There is no guarantee for compatibility between
 * different types of numbers, since that is implementation-dependent.
 */
public abstract class MyNumber<T> extends Primitive implements Comparable<MyNumber<T>> {

  protected final T number;

  /**
   * Creates a MyNumber based on given Representation for Numbers T.
   *
   * @param number the number to represent, as an instance of T.
   */
  protected MyNumber(T number) {
    this.number = Objects.requireNonNull(number);
  }

  /**
   * Adds two {@code Rationals} to produce a new {@code MyNumber}.
   *
   * @param other the other {@code MyNumber} to add to this one.
   * @return the sum of this {@code MyNumber} and the other one.
   * @throws ArithmeticError if there are any arithmetic errors encountered.
   */
  abstract public MyNumber<T> add(MyNumber<T> other) throws ArithmeticError;

  /**
   * Subtracts the other {@code MyNumber} from this {@code MyNumber}.
   *
   * @param other the other {@code MyNumber} to subtract from this one.
   * @return the difference of this {@code MyNumber} and the other one.
   * @throws ArithmeticError if there are any arithmetic errors encountered.
   */
  abstract public MyNumber<T> subtract(MyNumber<T> other) throws ArithmeticError;

  /**
   * Multiplies two {@code Rationals} to produce a new {@code MyNumber}.
   *
   * @param other the other {@code MyNumber} to multiply with this one.
   * @return this {@code MyNumber} times the other one.
   * @throws ArithmeticError if there are any arithmetic errors encountered.
   */
  abstract public MyNumber<T> multiply(MyNumber<T> other) throws ArithmeticError;

  /**
   * Divides the other {@code Rationals} from this {@code MyNumber}.
   *
   * @param other the other {@code MyNumber} to divide from this one.
   * @return the rational quotient of dividing this {@code MyNumber} by the other one.
   * @throws DivideByZeroError if the other {@code MyNumber} is 0.
   * @throws ArithmeticError   if there are any arithmetic errors encountered.
   */
  abstract public MyNumber<T> divide(MyNumber<T> other)
      throws DivideByZeroError, ArithmeticError;

  @Override
  public IType getType() {
    return PrimType.NUMBER;
  }

  @Override
  public String toString() {
    return this.number.toString();
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (o instanceof MyNumber) {
      MyNumber other = (MyNumber) o;
      return this.number.equals(other.number);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.number);
  }

  /**
   * Return the sign of this number.
   *
   * @return -1 if the number is negative, 1 if the number is positive and 0 otherwise.
   */
  public abstract int sign();
}
