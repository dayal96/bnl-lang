package io.github.dayal96.expression.operator.cons;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.cons.ConsPair;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import java.util.List;

public class Cons extends AOperator {

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {

    if (operands.size() != 2) {
      throw new Exception("cons : expected 2 arguments, found " + operands.size());
    }

    return new ConsPair(operands.get(0).evaluate(environment),
        operands.get(1).evaluate(environment));
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }

  @Override
  public String toString() {
    return "cons";
  }
}
