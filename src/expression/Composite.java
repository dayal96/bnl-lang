package expression;

import java.util.List;
import java.util.Objects;

import environment.IEnvironment;
import expression.operator.IOperator;

/**
 * Class to represent Composite Expressions.
 */
public class Composite implements IExpression {
  private IOperator operator;
  private List<IExpression> operands;

  /**
   * Creates a new Composite that combines the RHS and LHS values using the given operator.
   * @param operator    the operator to perform of RHS and LHS.
   * @param operands    the operands in the expression.
   * @param environment the environment for this Composite.
   */
  public Composite(IOperator operator, List<IExpression> operands, IEnvironment environment) {

    this.operator = operator;
    this.operands = operands;
  }

  @Override
  public IExpression evaluate() {
    return this.operator.operate(this.operands);
  }

  @Override
  public String getType() {
    return this.operator.getType();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    else if (o instanceof IExpression) {
      IExpression other = (IExpression)o;
      return other.evaluate().equals(this.evaluate());
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.operator, this.operands);
  }

  @Override
  public String toString() {

    StringBuilder operandsStrings = new StringBuilder("");

    for (IExpression e : this.operands) {
      operandsStrings.append(" " + e.toString());
    }

    return "(" + this.operator.toString() + operandsStrings + ")";
  }
}
