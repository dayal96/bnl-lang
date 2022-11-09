package io.github.dayal96.expression.local;

import static io.github.dayal96.expression.EmptyExpression.EMPTY_EXPR;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.operator.struct.StructAccessor;
import io.github.dayal96.expression.operator.struct.StructConstructor;
import io.github.dayal96.expression.operator.struct.StructPredicate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
    StructConstructor constructor = new StructConstructor(name, fields);
    StructPredicate predicate = new StructPredicate(constructor.structType);
    List<StructAccessor> accessors = new LinkedList<>();

    int curIdx = 0;
    for (var fieldName : fields) {
      accessors.add(new StructAccessor(constructor.structType, fieldName, curIdx));
      curIdx++;
    }

    environment.addEntry(constructor.toString(), constructor);
    environment.addEntry(predicate.toString(), predicate);
    accessors.forEach(acc -> environment.addEntry(acc.toString(), acc));
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof StructDefinition that) {
      return Objects.equals(this.name, that.name) &&
          Objects.equals(String.join("",this.fields), String.join("", that.fields));
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.fields.size());
  }

  @Override
  public String toString() {
    return "(define-struct " + name + " [" + String.join(" ", fields) + "])";
  }
}
