package io.github.dayal96.expression.operator.string;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.string.MyString;
import java.util.List;

public class StringAppend extends AOperator {

  public StringAppend() {}

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    if (operands.size() < 2) {
      throw new Exception("string-append : expected at least 2 argument, found " + operands.size());
    }

    StringBuilder result = new StringBuilder();

    for (var operand : operands) {
      var val = operand.evaluate(environment);
      PrimType.STRING.join(val.getType());
      result.append(val.toString());
    }

    return new MyString(result.toString());
  }

  @Override
  public String toString() {
    return "string-append";
  }
}
