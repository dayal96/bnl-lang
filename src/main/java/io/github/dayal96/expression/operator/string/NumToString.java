package io.github.dayal96.expression.operator.string;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.number.Rational;
import io.github.dayal96.primitive.string.MyString;
import java.util.LinkedList;
import java.util.List;

public class NumToString extends AOperator {

  public NumToString() {}

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    if (operands.size() != 1) {
      throw new Exception("num->string : expected 1 argument, found " + operands.size());
    }

    List<IExpression> evaluated = new LinkedList<>();
    for (var operand : operands) {
      evaluated.add(operand.evaluate(environment));
    }

    PrimType.NUMBER.join(evaluated.get(0).getType());
    return new MyString(evaluated.get(0).toString());
  }

  @Override
  public String toString() {
    return "num->string";
  }
}
