package io.github.dayal96.expression.local;

import static io.github.dayal96.expression.EmptyExpression.EMPTY_EXPR;

import io.github.dayal96.environment.Environment;
import java.util.List;

public class StructDefinition implements Definition {

  public final String name;
  public final List<String> fields;

  public StructDefinition(String name, List<String> fields) {
    this.name = name;
    this.fields = fields;
  }

  @Override
  public void addDefinitionNames(Environment environment) throws Exception {
    environment.addEntry("make-" + name, EMPTY_EXPR);
    environment.addEntry(name + "?", EMPTY_EXPR);
    for (var fieldName : fields) {
      environment.addEntry(name + "-" + fieldName, EMPTY_EXPR);
    }
  }

  @Override
  public void addDefinitionBodies(Environment environment) throws Exception {
    // TODO: Implement struct functions here
  }
}
