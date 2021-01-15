package expression.operator.number;

import environment.IEnvironment;
import expression.IExpression;
import expression.number.MyNumber;
import expression.operator.AOperator;
import expression.type.IType;
import expression.type.NilType;
import expression.type.PrimType;
import java.util.LinkedList;
import java.util.List;

public class Multiply extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {

    boolean allNumbers = true;

    List<IExpression> eval = new LinkedList<>();

    for (IExpression e : operands) {
      IExpression evaluated = e.evaluate(environment);
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

      for (int i = 1; i < operands.size(); i++) {
          result = result.multiply((MyNumber) (eval.get(i)));
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
    return "*";
  }
}
