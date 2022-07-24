package expression.operator.number;

import environment.IEnvironment;
import expression.IExpression;
import primitive.bool.MyBoolean;
import primitive.number.MyNumber;
import expression.operator.AOperator;
import expression.type.IType;
import expression.type.NilType;
import expression.type.PrimType;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to represent Addition.
 */
public class LessThan extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {

    boolean allNumbers = true;
    List<IExpression> eval = new LinkedList<>();

    for (IExpression e : operands) {
      IExpression evaluated = e.evaluate(environment);
      eval.add(evaluated);
      try {
        PrimType.NUMBER.join(evaluated.getType());
      }
      catch (Exception error) {
        allNumbers = false;
      }
    }

    if (!allNumbers) {
      throw new IllegalArgumentException("All operands must be numbers.");
    } else if (eval.size() >= 2) {
      boolean result = true;

      MyNumber prev = (MyNumber) eval.get(0);
      MyNumber current = (MyNumber) eval.get(0);

      for (int i = 1; i < eval.size(); i++) {
        current = (MyNumber) eval.get(i);
        result = result && (prev.compareTo(current) < 0);
        prev = current;
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
    return "<";
  }
}
