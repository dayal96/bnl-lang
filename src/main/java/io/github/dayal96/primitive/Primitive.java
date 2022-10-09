package io.github.dayal96.primitive;

import io.github.dayal96.absyn.AbsynVisitor;
import io.github.dayal96.absyn.Absyn;
import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
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
public abstract class Primitive implements Expression, Absyn {

  @Override
  public Expression evaluate(Environment environment) {
    return this;
  }

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    throw new Exception("Can't call a primitive as a function.");
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitPrimitive(this);
  }
}
