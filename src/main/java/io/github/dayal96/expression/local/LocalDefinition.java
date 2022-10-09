package io.github.dayal96.expression.local;

import io.github.dayal96.expression.Expression;

public class LocalDefinition {

  public final String name;
  public final Expression value;

  /**
   * Create a Definition which when evaluated, will add a definition to the environment.
   *
   * @param name  The identifier assigned to the definition.
   * @param value The value assigned to the definition.
   */
  public LocalDefinition(String name, Expression value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public String toString() {
    return "(define " + this.name + " " + this.value.toString() + ")";
  }
}
