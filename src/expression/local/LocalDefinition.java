package expression.local;

import expression.IExpression;

public class LocalDefinition {
  public final String name;
  public final IExpression value;

  /**
   * Create a Definition which when evaluated, will add a definition to the environment.
   * @param name        The identifier assigned to the definition.
   * @param value       The value assigned to the definition.
   */
  public LocalDefinition(String name, IExpression value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public String toString() {
    return "(define " + this.name + " " + this.value.toString() + ")";
  }
}
