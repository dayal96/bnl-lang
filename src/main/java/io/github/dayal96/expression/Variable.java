package io.github.dayal96.expression;

import io.github.dayal96.absyn.AbsynVisitor;
import io.github.dayal96.absyn.Absyn;
import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.visitor.ExpressionVisitor;
import java.util.List;

public class Variable implements Expression, Absyn {

  private final String name;

  /**
   * Create a Variable with a given identifier.
   *
   * @param name The identifier for the variable.
   */
  public Variable(String name) {
    this.name = name;
  }

  @Override
  public Expression evaluate(Environment environment) throws Exception {
    if (environment.isPresent(this.name)) {
      return environment.getEntry(this.name);
    } else {
      throw new Exception("Variable not found: " + this.name);
    }
  }

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    return this.evaluate(environment).evaluate(operands, environment);
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Variable that)) {
      return false;
    }

    return this.name.equals(that.name);
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visitVariable(this);
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitVariable(this);
  }
}
