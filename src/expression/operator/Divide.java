package expression.operator;

import java.util.List;

import exceptions.ArithmeticError;
import expression.IExpression;
import expression.number.MyNumber;

/**
 * Class to represent Division operation.
 */
public class Divide extends AOperator {

  @Override
  public IExpression operate(List<IExpression> operands) {

    boolean allNumbers = true;

    for (IExpression e : operands) {
      allNumbers = allNumbers && (e.getType() == "number");
    }

    if (!allNumbers) {
      throw new IllegalArgumentException("All operands must be numbers.");
    }
    else if (operands.size() == 1) {
      return operands.get(0).evaluate();
    }
    else if (operands.size() > 1) {
      MyNumber result = (MyNumber) (operands.get(0).evaluate());

      for (int i = 1; i < operands.size(); i++) {

        try {
          result = result.divide((MyNumber) (operands.get(i).evaluate()));
        }
        catch (ArithmeticError e) {
          e.printStackTrace();
        }
      }

      return result;
    }
    else {
      throw new IllegalArgumentException("Too few arguments for IOperator.");
    }
  }

  @Override
  public IExpression evaluate() {
    return this;
  }

  @Override
  public String getReturnType() {
    return "number";
  }

  @Override
  public String toString() {
    return "/";
  }
}
