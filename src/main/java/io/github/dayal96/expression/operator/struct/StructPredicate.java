package io.github.dayal96.expression.operator.struct;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.StructType;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;
import java.util.Objects;

public class StructPredicate extends AOperator {

  private final StructType structType;


  public StructPredicate(StructType structType) {
    this.structType = structType;
  }


  @Override
  public Expression evaluate(List<Expression> operands, Environment environment) throws Exception {
    if (operands.size() != 1) {
      throw new Exception(structType.name + "? : expected 1 argument, found " + operands.size());
    }

    try {
      operands.get(0).evaluate(environment).getType().join(structType);
      return MyBoolean.TRUE;
    } catch (Exception e) {
      return MyBoolean.FALSE;
    }
  }

  @Override
  public String toString() {
    return structType.name + "?";
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof StructPredicate that) {
      return this.structType.equals(that.structType);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.structType);
  }
}
