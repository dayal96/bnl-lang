package Expression.Operator;

import Expression.Expression;

/**
 * Interface to represent an Operator in an Composite.
 * An Operator is an operation that is performed on data, used to combine Primitives
 * or Expressions into a new Composite.
 *
 * E.G.: 4 + 2 is an expression, + is the operator.
 */
public interface Operator {

  /**
   * Perform the operation on given values.
   * @return the result of performing the operation on given values.
   */
  public Expression operate(Expression lhs, Expression rhs);

  /**
   * Get the return type of this Operator.
   * @return the return type of the Operator.
   */
  public String getType();

  @Override
  public String toString();
}
