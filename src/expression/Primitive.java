package expression;

import environment.IEnvironment;
import java.util.List;

/**
 * <p>Abstract Class to represent a Primitive quantity.</p>
 * <p>Primitives in ANL are:</p>
 * <ul>
 *   <li>Numbers</li>
 * </ul>
 */
public abstract class Primitive implements IExpression {

  @Override
  public IExpression evaluate(IEnvironment environment) {
    return this;
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    throw new Exception("Can't call a primitive as a function.");
  }
}
