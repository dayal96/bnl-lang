package io.github.dayal96.expression.operator.number;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.primitive.number.MyNumber;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.type.PrimType;
import java.util.LinkedList;
import java.util.List;

public class Subtract extends AOperator {

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {

    boolean allNumbers = true;

    List<Expression> eval = new LinkedList<>();

    for (Expression e : operands) {
      Expression evaluated = e.evaluate(environment);
      eval.add(evaluated);
      try {
        PrimType.NUMBER.join(evaluated.getType());
      }
      catch (Exception error) {
        allNumbers = false;
      }
    }

    if (!allNumbers) {
      throw new IllegalArgumentException("All operands must be numbers.");
    } else if (operands.size() == 1) {
      return eval.get(0);
    } else if (operands.size() > 1) {
      MyNumber result = (MyNumber) (eval.get(0));

      for (int i = 1; i < eval.size(); i++) {
          result = result.subtract((MyNumber) (eval.get(i)));
      }

      return result;
    } else {
      throw new IllegalArgumentException("Too few arguments for IOperator.");
    }
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }

  @Override
  public String toString() {
    return "-";
  }
}
