package Expression.Operator;

import java.util.Objects;

import Exceptions.ArithmeticError;
import Expression.Expression;
import Expression.Number.MyNumber;

/**
 * Class to represent Division operation.
 */
public class Divide implements Operator {
  @Override
  public Expression operate(Expression lhs, Expression rhs) {
    if (lhs.getType() == "Number" && rhs.getType() == "Number") {
      MyNumber lhsEval = ((MyNumber)lhs.evaluate());
      MyNumber rhsEval = ((MyNumber)rhs.evaluate());

      Expression ret = null;

      try {
        ret = lhsEval.divide(rhsEval);
        Objects.requireNonNull(ret);
      } catch (ArithmeticError e) {
        e.printStackTrace();
      } catch (NullPointerException e) {
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
    return "/";
  }
}
