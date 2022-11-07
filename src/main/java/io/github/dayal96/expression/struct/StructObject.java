package io.github.dayal96.expression.struct;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.visitor.ExpressionVisitor;
import java.util.List;
import java.util.Objects;

public final class StructObject implements Expression {

  public final String structName;
  public final List<Expression> values;

  public StructObject(String structName, List<Expression> values) {
    this.structName = structName;
    this.values = values;
  }

  @Override
  public Expression evaluate(Environment environment) throws Exception {
    return null;
  }

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment) throws Exception {
    throw new Exception("Struct " + structName + " can not be used as functions.");
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return null;
  }

  @Override
  public IType getType() {
    return null;
  }

  public String structName() {
    return structName;
  }

  public List<Expression> values() {
    return values;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null || obj.getClass() != this.getClass()) {
      return false;
    }
    var that = (StructObject) obj;
    return Objects.equals(this.structName, that.structName) &&
        Objects.equals(this.values, that.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(structName, values);
  }

  @Override
  public String toString() {
    return "StructObject[" +
        "structName=" + structName + ", " +
        "values=" + values + ']';
  }

}
