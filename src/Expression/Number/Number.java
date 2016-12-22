package Expression.Number;

import java.util.Objects;

import Exceptions.ArithmeticError;
import Exceptions.DivideByZeroError;
import Expression.Primitive;

/**
 * Interface to represent a Number in a given type. There is no guarantee for compatibility between
 * different types of numbers, since that is implementation-dependent.
 */
public abstract class Number<T> implements Primitive {

  protected final T number;

  /**
   * Creates a Number based on given Representation for Numbers T.
   * @param number  the number to represent, as an instance of T.
   */
  protected Number(T number) {
    this.number = Objects.requireNonNull(number);
  }

  /**
   * Adds two {@code Rationals} to produce a new {@code Number}.
   * @param other  the other {@code Number} to add to this one.
   * @return the sum of this {@code Number} and the other one.
   * @throws ArithmeticError if there are any arithmetic errors encountered.
   */
  abstract public Number<T> add(Number<T> other) throws ArithmeticError;

  /**
   * Subtracts the other {@code Number} from this {@code Number}.
   * @param other  the other {@code Number} to subtract from this one.
   * @return the difference of this {@code Number} and the other one.
   * @throws ArithmeticError if there are any arithmetic errors encountered.
   */
  abstract public Number<T> subtract(Number<T> other) throws ArithmeticError;

  /**
   * Multiplies two {@code Rationals} to produce a new {@code Number}.
   * @param other  the other {@code Number} to multiply with this one.
   * @return this {@code Number} times the other one.
   * @throws ArithmeticError if there are any arithmetic errors encountered.
   */
  abstract public Number<T> multiply(Number<T> other) throws ArithmeticError;

  /**
   * Divides the other {@code Rationals} from this {@code Number}.
   * @param other  the other {@code Number} to divide from this one.
   * @return the rational quotient of dividing this {@code Number} by the other one.
   * @throws DivideByZeroError if the other {@code Number} is 0.
   * @throws ArithmeticError if there are any arithmetic errors encountered.
   */
  abstract public Number<T> divide(Number<T> other)
          throws DivideByZeroError, ArithmeticError;

  @Override
  public String toString() {
    return this.number.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    else if (o instanceof Number) {
      Number other = (Number)o;
      return this.number.equals(other.number);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.number);
  }
}