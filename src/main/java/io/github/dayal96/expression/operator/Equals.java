package io.github.dayal96.expression.operator;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.primitive.bool.MyBoolean;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import java.util.List;

public class Equals extends AOperator {

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    if (operands.size() >= 2) {
      boolean result = true;
      int i = 1;
      Expression evaluated = operands.get(0).evaluate(environment);
      while (result && i < operands.size()) {
        result = operands.get(i++).evaluate(environment).equals(evaluated);
      }

      return MyBoolean.of(result);
    } else {
      throw new IllegalArgumentException("Too few arguments for IOperator.");
    }
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }

  @Override
  public String toString() {
    return "=";
  }
}
