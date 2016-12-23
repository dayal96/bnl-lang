package Expression.Operator;

import Expression.Value;

/**
 * Interface to represent an Operator in an Expression.
 * An Operator is an operation that is performed on data, used to combine Primitives
 * or Expressions into a new Expression.
 *
 * E.G.: 4 + 2 is an expression, + is the operator.
 */
public interface Operator {

  /**
   * Perform the operation on given values.
   * @return the result of performing the operation on given values.
   */
  public Value operate(Value lhs, Value rhs);

  /**
   * Get the return type of this Operator.
   * @return the return type of the Operator.
   */
  public String getType();
}
