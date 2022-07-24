package expression;

import environment.IEnvironment;
import expression.type.IType;
import expression.type.NilType;
import expression.type.PrimType;
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
