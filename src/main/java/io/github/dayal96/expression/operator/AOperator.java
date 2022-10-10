package io.github.dayal96.expression.operator;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.visitor.ExpressionVisitor;

public abstract class AOperator implements Expression {

  @Override
  public Expression evaluate(Environment environment) {
    return this;
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visitOperator(this);
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }
}
