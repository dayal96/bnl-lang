package io.github.dayal96.expression.operator.cons;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.cons.ConsPair;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import java.util.List;

public class Rest extends AOperator {

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    if (operands.size() != 1) {
      throw new Exception("rest : expected 1 argument, found " + operands.size());
    }

    Expression evaluated = operands.get(0).evaluate(environment);

    if (!evaluated.getType().equals(ConsPair.CONS_PAIR_TYPE)) {
      throw new Exception("Expected a cons-pair, found " + evaluated);
    }

    return ((ConsPair) evaluated).rest.evaluate(environment);
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }

  @Override
  public String toString() {
    return "rest";
  }
}
