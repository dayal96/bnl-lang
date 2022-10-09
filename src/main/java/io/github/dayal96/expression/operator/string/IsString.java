package io.github.dayal96.expression.operator.string;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;

public class IsString extends AOperator {
  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
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
  public String toString() {
    return "string?";
  }
}
