package io.github.dayal96.expression.local;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.EmptyExpression;
import io.github.dayal96.expression.Expression;

public record LocalDefinition(String name, Expression value) implements Definition {

  /**
   * Create a Definition which when evaluated, will add a definition to the environment.
   *
   * @param name  The identifier assigned to the definition.
   * @param value The value assigned to the definition.
   */
  public LocalDefinition {
  }

  @Override
  public String toString() {
    return "(define " + this.name + " " + this.value.toString() + ")";
  }

  @Override
  public void addDefinitionNames(Environment environment) {
    environment.addEntry(name, EmptyExpression.EMPTY_EXPR);
  }

  @Override
  public void addDefinitionBodies(Environment environment) throws Exception {
    environment.addEntry(name, value.evaluate(environment));
  }
}
