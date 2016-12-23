package Expression;

import Expression.Operator.Operator;

/**
 * Class to represent Expressions.
 */
public class Expression implements Value {
  private Operator operator;
  private Value lhs;
  private Value rhs;

  /**
   * Creates a new Expression that combines the RHS and LHS values using the given operator.
   * @param operator  the operator to perform of RHS and LHS.
   * @param lhs       the LHS of the expression.
   * @param rhs       the RHS of the expression.
   */
  public Expression(Operator operator, Value lhs, Value rhs) {

    this.operator = operator;
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public Value evaluate() {
    return this.operator.operate(this.lhs, this.rhs);
  }

  @Override
  public String getType() {
    return this.operator.getType();
  }
}
