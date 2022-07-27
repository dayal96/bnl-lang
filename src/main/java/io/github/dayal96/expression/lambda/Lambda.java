package io.github.dayal96.expression.lambda;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import java.util.List;

/**
 * Class to represent an expression that defines a function.
 * <p>
 * Lambda expressions are not functions, but function definitions. Once a lambda expression has been
 * evaluated in its context, it produces an enclosed function (an {@link LambdaEnclosure}) which is
 * a true function.
 */
public class Lambda implements IExpression {

  private final List<String> inputs;
  private final IExpression body;

  /**
   * Create a Lambda Expression that evaluates an expression body based on some inputs.
   *
   * @param inputs The variables used in the function as input.
   * @param body   The expression that composes the body of the function.
   */
  public Lambda(List<String> inputs, IExpression body) {
    this.inputs = inputs;
    this.body = body;
  }

  @Override
  public IExpression evaluate(IEnvironment environment) throws Exception {
    return new LambdaEnclosure(this.inputs, this.body, environment);
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    throw new Exception("Function called before it's definition was evaluated. "
        + "This should not be possible.");
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();

    str.append("(λ' ( ");

    for (String input : this.inputs) {
      str.append(input + " ");
    }

    str.append(") " + this.body.toString() + ")");

    return str.toString();
  }
}
