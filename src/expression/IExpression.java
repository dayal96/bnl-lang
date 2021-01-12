package expression;

import environment.IEnvironment;
import expression.type.Type;
import java.util.List;

/**
 * <p>Interface to represent a data value.</p>
 * <p>
 * A IExpression is one of:
 *   <ul>
 *     <li>Primitive</li>
 *     <li>FunctionCall</li>
 *   </ul>
 * </p>
 */
public interface IExpression {

  /**
   * Evaluate this IExpression to the simplest form possible.
   *
   * @return the simplest form of the IExpression.
   */
  IExpression evaluate(IEnvironment environment) throws Exception;

  /**
   * Evaluate the function with given input.
   *
   * @return the result of performing the operation on given values.
   */
  IExpression evaluate(List<IExpression> operands, IEnvironment environment) throws Exception;

  /**
   * Get the type of this IExpression.
   *
   * @return the String representing the type of this IExpression.
   */
  Type getType();

  @Override
  String toString();
}
