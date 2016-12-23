package Expression;

/**
 * <p>Interface to represent a data value.</p>
 * <p>
 *   A Value is one of:
 *   <ul>
 *     <li>Primitive</li>
 *     <li>Expression</li>
 *   </ul>
 * </p>
 */
public interface Value {

  /**
   * Evaluate this Value to the simplest form possible.
   * @return the simplest form of the Value.
   */
  public Value evaluate();

  /**
   * Get the type of this Value.
   * @return the String representing the type of this Value.
   */
  public String getType();
}
