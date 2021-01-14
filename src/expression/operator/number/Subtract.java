package expression.operator.number;

import environment.IEnvironment;
import exceptions.ArithmeticError;
import expression.IExpression;
import expression.number.MyNumber;
import expression.operator.AOperator;
import expression.type.Type;
import java.util.LinkedList;
import java.util.List;

public class Subtract extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {

    boolean allNumbers = true;

    List<IExpression> eval = new LinkedList<>();

    for (IExpression e : operands) {
      IExpression evaluated = e.evaluate(environment);
      eval.add(evaluated);
      allNumbers = allNumbers && (evaluated.getType().equals(Type.NUMBER));
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
  public Type getType() {
    return Type.NUMBER;
  }

  @Override
  public String toString() {
    return "-";
  }
}
