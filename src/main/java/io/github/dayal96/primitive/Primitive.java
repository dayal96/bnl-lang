package io.github.dayal96.primitive;

import io.github.dayal96.absyn.AbsynVisitor;
import io.github.dayal96.absyn.IAbsyn;
import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import java.util.List;

/**
 * <p>Abstract Class to represent a Primitive quantity.</p>
 * <p>Primitives in ANL are:</p>
 * <ul>
 *   <li>Numbers</li>
 *   <li>Booleans</li>
 *   <li>String</li>
 * </ul>
 */
public abstract class Primitive implements IExpression, IAbsyn {

  @Override
  public IExpression evaluate(IEnvironment environment) {
    return this;
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    throw new Exception("Can't call a primitive as a function.");
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitPrimitive(this);
  }
}
