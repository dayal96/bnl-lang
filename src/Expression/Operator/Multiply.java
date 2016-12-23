package Expression.Operator;

import java.util.Objects;

import Exceptions.ArithmeticError;
import Expression.Number.MyNumber;
import Expression.Value;

/**
 * Created by amoghlaptop on 23/12/16.
 */
public class Multiply implements Operator {
  @Override
  public Value operate(Value lhs, Value rhs) {
    Value lhsEval = lhs.evaluate();
    Value rhsEval = rhs.evaluate();

    if (lhsEval.getType() == "Number" && rhsEval.getType() == "Number") {
      Value ret = null;
      try {
        ret = ((MyNumber) lhsEval).multiply((MyNumber) rhsEval);
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
}
