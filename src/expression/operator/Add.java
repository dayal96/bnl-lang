package expression.operator;

import java.util.List;

import environment.IEnvironment;
import exceptions.ArithmeticError;
import expression.IExpression;
import expression.number.MyNumber;
import expression.type.Type;

/**
 * Class to represent Addition.
 */
public class Add extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment) throws Exception {

    boolean allNumbers = true;

    for (IExpression e : operands) {
      allNumbers = allNumbers && (e.getType().equals(Type.NUMBER));
    }

    if (!allNumbers) {
      throw new IllegalArgumentException("All operands must be numbers.");
    }
    else if (operands.size() == 1) {
      return operands.get(0).evaluate(environment);
    }
    else if (operands.size() > 1) {
      MyNumber result = (MyNumber)(operands.get(0).evaluate(environment));

      for (int i = 1; i < operands.size(); i++) {

        try {
          result = result.add((MyNumber) (operands.get(i).evaluate(environment)));
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
  public Type getType() {
    return Type.NUMBER;
  }

  @Override
  public String toString() {
    return "+";
  }
}
