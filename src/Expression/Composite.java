package Expression;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Exchanger;

import Expression.Operator.Operator;

/**
 * Class to represent Composite Expressions.
 */
public class Composite implements Expression {
  private Operator operator;
  private List<Expression> operands;

  /**
   * Creates a new Composite that combines the RHS and LHS values using the given operator.
   * @param operator  the operator to perform of RHS and LHS.
   * @param operands  the operands in the expression.
   */
  public Composite(Operator operator, List<Expression> operands) {

    this.operator = operator;
    this.operands = operands;
  }

  @Override
  public Expression evaluate() {
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
    else if (o instanceof Expression) {
      Expression other = (Expression)o;
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

    for (Expression e : this.operands) {
      operandsStrings.append(" " + e.toString());
    }

    return "(" + this.operator.toString() + operandsStrings + ")";
  }
}
