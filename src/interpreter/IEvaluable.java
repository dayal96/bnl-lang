package interpreter;

import environment.IEnvironment;
import expression.IExpression;
import java.util.Optional;

public interface IEvaluable {

  /**
   * Evaluate this evaluable.
   */
  public Optional<IExpression> evaluate(IEnvironment environment) throws Exception;
}
