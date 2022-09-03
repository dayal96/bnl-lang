package io.github.dayal96.interpreter;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import java.util.Optional;

public class EvaluableExpression implements IEvaluable {

  private final IExpression toEvaluate;

  /**
   * Create an Evaluable Expression.
   *
   * @param toEvaluate The IExpression this Evaluable will evaluate.
   */
  public EvaluableExpression(IExpression toEvaluate) {
    this.toEvaluate = toEvaluate;
  }

  @Override
  public Optional<IExpression> evaluate(IEnvironment environment) throws Exception {
    return Optional.of(toEvaluate.evaluate(environment));
  }

  @Override
  public String toString() {
    return this.toEvaluate.toString();
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof EvaluableExpression)) {
      return false;
    }

    EvaluableExpression that = (EvaluableExpression) other;
    return this.toEvaluate.equals(that.toEvaluate);
  }
}
