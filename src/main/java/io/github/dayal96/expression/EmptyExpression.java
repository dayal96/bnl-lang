package io.github.dayal96.expression;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.visitor.ExpressionVisitor;
import java.util.List;

public class EmptyExpression implements Expression {

  public static EmptyExpression EMPTY_EXPR = new EmptyExpression();

  private EmptyExpression() {}

  @Override
  public Expression evaluate(Environment environment) throws Exception {
    throw new Exception("Evaluating a placeholder Expression.");
  }

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    throw new Exception("Calling a placeholder Expression.");
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visitEmptyExpression(this);
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }
}
