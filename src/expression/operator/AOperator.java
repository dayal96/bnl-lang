package expression.operator;

import expression.IExpression;
import expression.type.Type;

public abstract class AOperator implements IOperator {

  @Override
  public IExpression evaluate() {
    return this;
  }

  @Override
  public String getType() {
    return Type.OPERATOR;
  }
}
