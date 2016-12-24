package Expression.Operator;

import java.util.Objects;

import Exceptions.ArithmeticError;
import Expression.Expression;
import Expression.Number.MyNumber;

/**
 * Created by amoghlaptop on 23/12/16.
 */
public class Add implements Operator {
  @Override
  public Expression operate(Expression lhs, Expression rhs) {

    Expression lhsEval = lhs.evaluate();
    Expression rhsEval = rhs.evaluate();

    if (lhsEval.getType() == "Number" && rhsEval.getType() == "Number") {
      Expression ret = null;
      try {
        ret = ((MyNumber) lhsEval).add((MyNumber) rhsEval);
        Objects.requireNonNull(ret);
      }
      catch (ArithmeticError e) {
        e.printStackTrace();
      }
      catch (NullPointerException e) {
        e.printStackTrace();
      }

      return ret;
    }
    else {
      throw new IllegalArgumentException("One of the arguments was not a Number.");
    }
  }

  @Override
  public String getType() {
    return "Number";
  }

  @Override
  public String toString() {
    return "+";
  }
}
