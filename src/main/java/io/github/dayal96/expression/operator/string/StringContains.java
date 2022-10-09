package io.github.dayal96.expression.operator.string;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;

public class StringContains extends AOperator {
  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    if (operands.size() != 2) {
      throw new Exception("string-contains? : expected 2 argument, found " + operands.size());
    }
    IExpression containing = operands.get(0).evaluate(environment);
    IExpression contained = operands.get(1).evaluate(environment);
    PrimType.STRING.join(containing.getType());
    PrimType.STRING.join(contained.getType());

    return MyBoolean.of(containing.toString().contains(contained.toString()));
  }

  @Override
  public String toString() {
    return "string-contains?";
  }
}
