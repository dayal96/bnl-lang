package expression.operator;

import environment.IEnvironment;
import expression.IExpression;
import expression.type.Type;

public abstract class AOperator implements IExpression {

  @Override
  public IExpression evaluate(IEnvironment environment) {
    return this;
  }

  @Override
  public Type getType() {
    return Type.FUNCTION;
  }
}
