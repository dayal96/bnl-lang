package io.github.dayal96.expression.lambda;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.type.IType;
import java.util.List;
import java.util.Objects;

/**
 * Class to represent Function Call Expressions.
 */
public class FunctionCall implements Expression {

  private final Expression operator;
  private final List<Expression> operands;

  /**
   * Creates a new FunctionCall that combines the RHS and LHS values using the given operator.
   *
   * @param operator The operator to perform of RHS and LHS.
   * @param operands The operands in the expression.
   */
  public FunctionCall(Expression operator, List<Expression> operands) {
    this.operator = operator;
    this.operands = operands;
  }

  @Override
  public Expression evaluate(Environment environment) throws Exception {
    return this.operator.evaluate(environment).evaluate(this.operands, environment);
  }

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    throw new Exception("I am impressed you managed to reach this error, but it all ends now"
        + "<evillaughter>MWAHAHAHAHA</evillaughter>");
  }

  @Override
  public IType getType() {
    return this.operator.getType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.operator, this.operands);
  }

  @Override
  public String toString() {

    StringBuilder operandsStrings = new StringBuilder();

    for (Expression e : this.operands) {
      operandsStrings.append(" " + e.toString());
    }

    return "(" + this.operator.toString() + operandsStrings + ")";
  }
}
