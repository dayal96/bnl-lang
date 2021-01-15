package expression.operator.bool;

import environment.IEnvironment;
import expression.IExpression;
import expression.bool.MyBoolean;
import expression.operator.AOperator;
import expression.type.PrimType;
import java.util.LinkedList;
import java.util.List;

public class Or extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    boolean allBoolean = true;
    List<IExpression> eval = new LinkedList<>();

    for (IExpression e : operands) {
      IExpression evaluated = e.evaluate(environment);
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
