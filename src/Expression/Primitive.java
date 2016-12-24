package Expression;

/**
 * <p>Abstract Class to represent a Primitive quantity.</p>
 * <p>Primitives in ANL are:</p>
 * <ul>
 *   <li>Numbers</li>
 * </ul>
 */
public abstract class Primitive implements Expression {

  @Override
  public Expression evaluate() {
    return this;
  }
}