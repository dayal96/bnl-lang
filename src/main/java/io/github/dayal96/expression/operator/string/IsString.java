package io.github.dayal96.expression.operator.string;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;

public class IsString extends AOperator {
  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    if (operands.size() != 1) {
      throw new Exception("string? : expected 1 argument, found " + operands.size());
    }

    try {
      PrimType.STRING.join(operands.get(0).evaluate(environment).getType());
    } catch (Exception e) {
      return MyBoolean.FALSE;
    }

    return MyBoolean.TRUE;
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }

  @Override
  public String toString() {
    return "string?";
  }
}
