package io.github.dayal96.expression;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import java.util.List;

public class EmptyExpression implements IExpression {

  public static EmptyExpression EMPTY_EXPR = new EmptyExpression();

  private EmptyExpression() {}

  @Override
  public IExpression evaluate(IEnvironment environment) throws Exception {
    throw new Exception("Evaluating a placeholder Expression.");
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    throw new Exception("Calling a placeholder Expression.");
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }
}
