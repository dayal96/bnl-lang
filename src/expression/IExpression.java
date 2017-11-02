package expression;

/**
 * <p>Interface to represent a data value.</p>
 * <p>
 *   A IExpression is one of:
 *   <ul>
 *     <li>Primitive</li>
 *     <li>Composite</li>
 *   </ul>
 * </p>
 */
public interface IExpression {

  /**
   * Evaluate this IExpression to the simplest form possible.
   * @return the simplest form of the IExpression.
   */
  abstract public IExpression evaluate();

  /**
   * Get the type of this IExpression.
   * @return the String representing the type of this IExpression.
   */
  abstract public String getType();
}
