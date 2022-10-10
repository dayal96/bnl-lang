package io.github.dayal96.interpreter;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import java.util.Optional;

public class EvaluableExpression implements Evaluable {

  private final Expression toEvaluate;

  /**
   * Create an Evaluable Expression.
   *
   * @param toEvaluate The Expression this Evaluable will evaluate.
   */
  public EvaluableExpression(Expression toEvaluate) {
    this.toEvaluate = toEvaluate;
  }

  @Override
  public Optional<Expression> evaluate(Environment environment) throws Exception {
    return Optional.of(toEvaluate.evaluate(environment));
  }

  @Override
  public String toString() {
    return this.toEvaluate.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof EvaluableExpression that)) {
      return false;
    }

    return this.toEvaluate.equals(that.toEvaluate);
  }
}
