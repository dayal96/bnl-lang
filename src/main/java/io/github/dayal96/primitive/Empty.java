package io.github.dayal96.primitive;

import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.StructType;
import java.util.List;

public class Empty extends Primitive {

  public static final Empty EMPTY = new Empty();

  private final IType type;

  private Empty() {
    this.type = new StructType("empty", List.of(), List.of());
  }

  @Override
  public IType getType() {
    return this.type;
  }

  @Override
  public <T> T accept(PrimitiveVisitor<T> visitor) {
    return visitor.visitEmpty(this);
  }

  @Override
  public String toString() {
    return "empty";
  }
}
