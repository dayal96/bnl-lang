package io.github.dayal96.interpreter;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import java.util.Optional;

public interface IEvaluable {

  /**
   * Evaluate this evaluable.
   */
  public Optional<IExpression> evaluate(IEnvironment environment) throws Exception;
}
