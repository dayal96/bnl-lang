package expression.operator;

import environment.IEnvironment;
import expression.IExpression;
import expression.type.IType;
import expression.type.NilType;
import expression.type.PrimType;

public abstract class AOperator implements IExpression {

  @Override
  public IExpression evaluate(IEnvironment environment) {
    return this;
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }
}
