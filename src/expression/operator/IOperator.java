package expression.operator;

import java.util.List;

import expression.IExpression;

/**
 * Interface to represent an IOperator in an Composite.
 * An IOperator is an operation that is performed on data, used to combine Primitives
 * or Expressions into a new Composite.
 *
 * E.G.: 4 + 2 is an expression, + is the operator.
 */
public interface IOperator extends IExpression {

  /**
   * Perform the operation on given values.
   * @return the result of performing the operation on given values.
   */
  public IExpression operate(List<IExpression> operands);

  /**
   * Get the return type of this IOperator.
   * @return the return type of the IOperator.
   */
  public String getType();

  @Override
  public String toString();
}
