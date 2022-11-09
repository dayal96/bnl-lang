package io.github.dayal96.expression.operator.struct;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.struct.StructObject;
import io.github.dayal96.expression.type.StructType;
import io.github.dayal96.expression.visitor.PartialExpressionVisitor;
import java.util.List;
import java.util.Objects;

public class StructAccessor extends AOperator {

  private final StructFieldGetter fieldGetter;
  private final String getterName;
  private final StructType structType;

  public StructAccessor(StructType structType, String fieldName, int fieldIndex) {
    this.fieldGetter = new StructFieldGetter(structType, fieldIndex);
    this.getterName = structType.name + "-" + fieldName;
    this.structType = structType;
  }


  @Override
  public Expression evaluate(List<Expression> operands, Environment environment) throws Exception {
    if (operands.size() != 1) {
      throw new Exception(this.getterName + " : expected 1 argument, found " + operands.size());
    }
    Expression evaluated = operands.get(0).evaluate(environment);
    this.structType.join(evaluated.getType());
    return evaluated.accept(fieldGetter).evaluate(environment);
  }

  @Override
  public String toString() {
    return this.getterName;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof StructAccessor that) {
      return Objects.equals(this.getterName, that.getterName)
          && Objects.equals(this.structType, that.structType)
          && this.fieldGetter.fieldIndex == that.fieldGetter.fieldIndex;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getterName, this.structType.hashCode(), this.fieldGetter.fieldIndex);
  }

  private static class StructFieldGetter extends PartialExpressionVisitor<Expression> {
    private final int fieldIndex;

    public StructFieldGetter(StructType structType, int fieldIndex) {
      super("Type Mismatch : expected " + structType);
      this.fieldIndex = fieldIndex;

      if (fieldIndex < 0) {
        throw new IllegalArgumentException("Field indices cannot be less than 0");
      }

      if (fieldIndex >= structType.fields.size()) {
        throw new IllegalArgumentException("Field indices cannot be greater than the "
            + "struct's number of fields");
      }
    }

    @Override
    public Expression visitStruct(StructObject structObject) {
      return structObject.values.get(fieldIndex);
    }
  }
}
