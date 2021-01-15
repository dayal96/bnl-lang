package expression.operator.number;

import environment.IEnvironment;
import expression.IExpression;
import expression.number.MyNumber;
import expression.operator.AOperator;
import expression.type.IType;
import expression.type.NilType;
import expression.type.PrimType;
import java.util.List;

public class Divide extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {

    boolean allNumbers = true;

    for (IExpression e : operands) {
      try {
        PrimType.NUMBER.join(e.getType());
      }
      catch (Exception error) {
        allNumbers = false;
        break;
      }
    }

    if (!allNumbers) {
      throw new IllegalArgumentException("All operands must be numbers.");
    } else if (operands.size() == 1) {
      return operands.get(0).evaluate(environment);
    } else if (operands.size() > 1) {
      MyNumber result = (MyNumber) (operands.get(0).evaluate(environment));

      for (int i = 1; i < operands.size(); i++) {
        result = result.divide((MyNumber) (operands.get(i).evaluate(environment)));
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
    return "/";
  }
}
