package expression.lambda;

import java.util.List;
import java.util.Objects;

import environment.IEnvironment;
import expression.IExpression;
import expression.type.Type;

/**
 * Class to represent Function Call Expressions.
 */
public class FunctionCall implements IExpression {
  private final IExpression operator;
  private final List<IExpression> operands;

  /**
   * Creates a new FunctionCall that combines the RHS and LHS values using the given operator.
   * @param operator    The operator to perform of RHS and LHS.
   * @param operands    The operands in the expression.
   */
  public FunctionCall(IExpression operator, List<IExpression> operands) {
    this.operator = operator;
    this.operands = operands;
  }

  @Override
  public IExpression evaluate(IEnvironment environment) throws Exception {
    return this.operator.evaluate(this.operands, environment);
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment) throws Exception {
    throw new Exception("You can't do that.");
  }

  @Override
  public Type getType() {
    return Type.VARIABLE;
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
