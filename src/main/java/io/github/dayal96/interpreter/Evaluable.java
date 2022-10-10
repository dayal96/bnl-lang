package io.github.dayal96.interpreter;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import java.util.Optional;

public interface Evaluable {

  /**
   * Evaluate this evaluable.
   */
  Optional<Expression> evaluate(Environment environment) throws Exception;
}
