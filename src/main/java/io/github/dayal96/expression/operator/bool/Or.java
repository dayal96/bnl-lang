package io.github.dayal96.expression.operator.bool;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.primitive.bool.MyBoolean;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.PrimType;
import java.util.LinkedList;
import java.util.List;

public class Or extends AOperator {

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    boolean allBoolean = true;
    List<Expression> eval = new LinkedList<>();

    for (Expression e : operands) {
      Expression evaluated = e.evaluate(environment);
      eval.add(evaluated);
      allBoolean = allBoolean && (evaluated.getType().equals(PrimType.BOOLEAN));
    }

    if (!allBoolean) {
      throw new IllegalArgumentException("All operands must be boolean.");
    } else if (operands.size() == 1) {
      return eval.get(0);
    } else if (operands.size() > 1) {
      boolean result = ((MyBoolean) eval.get(0)).truth();

      for (int i = 1; i < operands.size(); i++) {
        result = result || ((MyBoolean) eval.get(i)).truth();
      }

      return MyBoolean.of(result);
    } else {
      throw new IllegalArgumentException("Too few arguments for IOperator.");
    }
  }
}
