package io.github.dayal96.expression.operator;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.cons.ConsPair;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.primitive.Empty;
import java.util.List;

public class ListOperator extends AOperator {
  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    Expression list = Empty.EMPTY;

    for (int i = operands.size() - 1; i >= 0; i--) {
      Expression evaluated = operands.get(i).evaluate(environment);
      list = new ConsPair(evaluated, list);
    }

    return list;
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }

  @Override
  public String toString() {
    return "list";
  }
}
