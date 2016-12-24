package Expression;

import java.util.Objects;

/**
 * <p>Interface to represent a data value.</p>
 * <p>
 *   A Expression is one of:
 *   <ul>
 *     <li>Primitive</li>
 *     <li>Composite</li>
 *   </ul>
 * </p>
 */
public interface Expression {

  /**
   * Evaluate this Expression to the simplest form possible.
   * @return the simplest form of the Expression.
   */
  abstract public Expression evaluate();

  /**
   * Get the type of this Expression.
   * @return the String representing the type of this Expression.
   */
  abstract public String getType();
}
