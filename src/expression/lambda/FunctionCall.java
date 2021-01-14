package expression.lambda;

import environment.IEnvironment;
import expression.IExpression;
import expression.type.Type;
import java.util.List;
import java.util.Objects;

/**
 * Class to represent Function Call Expressions.
 */
public class FunctionCall implements IExpression {

  private final IExpression operator;
  private final List<IExpression> operands;

  /**
   * Creates a new FunctionCall that combines the RHS and LHS values using the given operator.
   *
   * @param operator The operator to perform of RHS and LHS.
   * @param operands The operands in the expression.
   */
  public FunctionCall(IExpression operator, List<IExpression> operands) {
    this.operator = operator;
    this.operands = operands;
  }

  @Override
  public IExpression evaluate(IEnvironment environment) throws Exception {
    return this.operator.evaluate(environment).evaluate(this.operands, environment);
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    throw new Exception("I am impressed you managed to reach this error, but it all ends now"
        + "<evillaughter>MWAHAHAHAHA</evillaughter>");
  }

  @Override
  public Type getType() {
    return this.operator.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.operator, this.operands);
  }

  @Override
  public String toString() {

    StringBuilder operandsStrings = new StringBuilder();

    for (IExpression e : this.operands) {
      operandsStrings.append(" " + e.toString());
    }

    return "(" + this.operator.toString() + operandsStrings + ")";
  }
}
