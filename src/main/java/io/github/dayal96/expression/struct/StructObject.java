package io.github.dayal96.expression.struct;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.StructType;
import io.github.dayal96.expression.visitor.ExpressionVisitor;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class StructObject implements Expression {

  public final StructType structType;
  public final List<Expression> values;

  public StructObject(StructType structType, List<Expression> values) {
    this.structType = structType;
    this.values = values;
  }

  @Override
  public Expression evaluate(Environment environment) throws Exception {
    List<Expression> evaluatedValues = new LinkedList<>();
    for (var exp : values) {
      evaluatedValues.add(exp.evaluate(environment));
    }
    return new StructObject(structType, evaluatedValues);
  }

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment) throws Exception {
    throw new Exception("Structs can not be used as functions.");
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visitStruct(this);
  }

  @Override
  public IType getType() {
    return this.structType;
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
    return Objects.equals(this.structType, that.structType) &&
        Objects.equals(this.values, that.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(structType.name, values);
  }

  @Override
  public String toString() {
    return "(make-" + structType.name + " "
        + values.stream().map(Expression::toString).collect(Collectors.joining(" "))
        + ")";
  }
}
