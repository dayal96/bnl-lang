package io.github.dayal96.expression.operator.number;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.primitive.number.MyNumber;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.type.PrimType;
import java.util.List;

public class Divide extends AOperator {

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {

    boolean allNumbers = true;

    for (Expression e : operands) {
      try {
        PrimType.NUMBER.join(e.getType());
      }
      catch (Exception error) {
        allNumbers = false;
        break;
      }
    }

    if (!allNumbers) {
      throw new IllegalArgumentException("All operands must be numbers.");
    } else if (operands.size() == 1) {
      return operands.get(0).evaluate(environment);
    } else if (operands.size() > 1) {
      MyNumber result = (MyNumber) (operands.get(0).evaluate(environment));

      for (int i = 1; i < operands.size(); i++) {
        result = result.divide((MyNumber) (operands.get(i).evaluate(environment)));
      }

      return result;
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
    return "/";
  }
}
