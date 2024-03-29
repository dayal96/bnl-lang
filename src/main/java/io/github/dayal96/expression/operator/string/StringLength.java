package io.github.dayal96.expression.operator.string;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.number.Rational;
import io.github.dayal96.primitive.string.MyString;
import java.util.List;

public class StringLength extends AOperator {

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    if (operands.size() != 1) {
      throw new Exception("string-length : expected 1 argument, found " + operands.size());
    }
    Expression evaluated = operands.get(0).evaluate(environment);
    PrimType.STRING.join(evaluated.getType());
    return new Rational(((MyString) evaluated).value.length());
  }

  @Override
  public String toString() {
    return "string-length";
  }
}
