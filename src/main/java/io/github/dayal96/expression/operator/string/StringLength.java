package io.github.dayal96.expression.operator.string;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.number.Rational;
import java.util.List;

public class StringLength extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    if (operands.size() != 1) {
      throw new Exception("string-length : expected 1 argument, found " + operands.size());
    }
    IExpression evaluated = operands.get(0).evaluate(environment);
    PrimType.STRING.join(evaluated.getType());
    return new Rational(evaluated.toString().length());
  }

  @Override
  public String toString() {
    return "string-length";
  }
}
