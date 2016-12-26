package Expression.Operator;

import java.util.List;

import Exceptions.ArithmeticError;
import Expression.Expression;
import Expression.Number.MyNumber;

/**
 * Class to represent Multiplication.
 */
public class Multiply implements Operator {

  @Override
  public Expression operate(List<Expression> operands) {

    boolean allNumbers = true;

    for (Expression e : operands) {
      allNumbers = allNumbers && (e.getType() == "Number");
    }

    if (!allNumbers) {
      throw new IllegalArgumentException("All operands must be numbers.");
    }
    else if (operands.size() == 1) {
      return operands.get(0).evaluate();
    }
    else if (operands.size() > 1) {
      MyNumber result = (MyNumber)(operands.get(0).evaluate());

      for (int i = 1; i < operands.size(); i++) {

        try {
          result = result.multiply((MyNumber) (operands.get(i).evaluate()));
        }
        catch (ArithmeticError e) {
          e.printStackTrace();
        }
      }

      return result;
    }
    else {
      throw new IllegalArgumentException("Too few arguments for Operator.");
    }
  }

  @Override
  public String getType() {
    return "Number";
  }

  @Override
  public String toString() {
    return "*";
  }
}
