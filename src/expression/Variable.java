package expression;

import environment.IEnvironment;
import expression.type.Type;
import java.util.List;

public class Variable implements IExpression {

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
  public IExpression evaluate(IEnvironment environment) throws Exception {
    if (environment.isPresent(this.name)) {
      return environment.getEntry(this.name);
    } else {
      throw new Exception("Variable not found: " + this.name);
    }
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    return this.evaluate(environment).evaluate(operands, environment);
  }

  @Override
  public Type getType() {
    return Type.VARIABLE;
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Variable)) {
      return false;
    }

    Variable that = (Variable) other;

    return this.name.equals(that.name);
  }
}
