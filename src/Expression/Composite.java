package Expression;

import java.util.Objects;
import java.util.concurrent.Exchanger;

import Expression.Operator.Operator;

/**
 * Class to represent Composite Expressions.
 */
public class Composite implements Expression {
  private Operator operator;
  private Expression lhs;
  private Expression rhs;

  /**
   * Creates a new Composite that combines the RHS and LHS values using the given operator.
   * @param operator  the operator to perform of RHS and LHS.
   * @param lhs       the LHS of the expression.
   * @param rhs       the RHS of the expression.
   */
  public Composite(Operator operator, Expression lhs, Expression rhs) {

    this.operator = operator;
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public Expression evaluate() {
    return this.operator.operate(this.lhs.evaluate(), this.rhs.evaluate());
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
    return Objects.hash(this.operator, this.lhs, this.rhs);
  }

  @Override
  public String toString() {
    return "(" + this.operator.toString() + " " + this.lhs.toString() + " " + this.rhs.toString()
            + ")";
  }
}
