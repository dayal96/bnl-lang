package io.github.dayal96.primitive.number;

import io.github.dayal96.exceptions.ArithmeticError;
import io.github.dayal96.exceptions.DivideByZeroError;
import io.github.dayal96.primitive.Primitive;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.PrimitiveVisitor;
import java.util.Objects;

/**
 * Interface to represent a number in a given type. There is no guarantee for compatibility between
 * different types of numbers, since that is implementation-dependent.
 */
public abstract class MyNumber<N> extends Primitive implements Comparable<MyNumber<N>> {

  public final N number;

  /**
   * Creates a MyNumber based on given Representation for Numbers T.
   *
   * @param number the number to represent, as an instance of T.
   */
  protected MyNumber(N number) {
    this.number = Objects.requireNonNull(number);
  }

  /**
   * Adds two {@code Rationals} to produce a new {@code MyNumber}.
   *
   * @param other the other {@code MyNumber} to add to this one.
   * @return the sum of this {@code MyNumber} and the other one.
   * @throws ArithmeticError if there are any arithmetic errors encountered.
   */
  abstract public MyNumber<N> add(MyNumber<N> other) throws ArithmeticError;

  /**
   * Subtracts the other {@code MyNumber} from this {@code MyNumber}.
   *
   * @param other the other {@code MyNumber} to subtract from this one.
   * @return the difference of this {@code MyNumber} and the other one.
   * @throws ArithmeticError if there are any arithmetic errors encountered.
   */
  abstract public MyNumber<N> subtract(MyNumber<N> other) throws ArithmeticError;

  /**
   * Multiplies two {@code Rationals} to produce a new {@code MyNumber}.
   *
   * @param other the other {@code MyNumber} to multiply with this one.
   * @return this {@code MyNumber} times the other one.
   * @throws ArithmeticError if there are any arithmetic errors encountered.
   */
  abstract public MyNumber<N> multiply(MyNumber<N> other) throws ArithmeticError;

  /**
   * Divides the other {@code Rationals} from this {@code MyNumber}.
   *
   * @param other the other {@code MyNumber} to divide from this one.
   * @return the rational quotient of dividing this {@code MyNumber} by the other one.
   * @throws DivideByZeroError if the other {@code MyNumber} is 0.
   * @throws ArithmeticError   if there are any arithmetic errors encountered.
   */
  abstract public MyNumber<N> divide(MyNumber<N> other)
      throws DivideByZeroError, ArithmeticError;

  @Override
  public <T> T accept(PrimitiveVisitor<T> visitor) {
    return visitor.visitMyNumber(this);
  }

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
    } else if (o instanceof MyNumber other) {
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
