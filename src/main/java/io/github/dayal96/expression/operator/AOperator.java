package io.github.dayal96.expression.operator;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;

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
