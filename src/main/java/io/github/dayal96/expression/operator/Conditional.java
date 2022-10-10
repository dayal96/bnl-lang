package io.github.dayal96.expression.operator;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;

public class Conditional extends AOperator {

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    if (operands.size() != 3) {
      throw new Exception("A conditional expects 3 parts, provided " + operands.size());
    } else {
      Expression truth = operands.get(0).evaluate(environment);

      if (truth.equals(MyBoolean.TRUE)) {
        return operands.get(1).evaluate(environment);
      } else if (truth.equals(MyBoolean.FALSE)) {
        return operands.get(2).evaluate(environment);
      } else {
        throw new Exception(truth + " is not a Boolean.");
      }
    }
  }

  @Override
  public String toString() {
    return "if";
  }
}
