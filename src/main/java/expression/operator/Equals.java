package expression.operator;

import environment.IEnvironment;
import expression.IExpression;
import primitive.bool.MyBoolean;
import expression.type.IType;
import expression.type.NilType;
import java.util.List;

public class Equals extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    if (operands.size() >= 2) {
      boolean result = true;

      for (int i = 1; i < operands.size(); i++) {
        result = result && operands.get(i).evaluate(environment)
            .equals(operands.get(0).evaluate(environment));
      }

      return MyBoolean.of(result);
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
    return "=";
  }
}
