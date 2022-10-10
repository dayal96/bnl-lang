package io.github.dayal96.expression.operator.string;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.string.MyString;
import java.util.List;

public class StringAppend extends AOperator {

  public StringAppend() {}

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    if (operands.size() < 2) {
      throw new Exception("string-append : expected at least 2 argument, found " + operands.size());
    }

    StringBuilder result = new StringBuilder();

    for (var operand : operands) {
      var val = operand.evaluate(environment);
      PrimType.STRING.join(val.getType());
      result.append(val);
    }

    return new MyString(result.toString());
  }

  @Override
  public String toString() {
    return "string-append";
  }
}
