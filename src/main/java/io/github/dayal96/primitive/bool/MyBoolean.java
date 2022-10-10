package io.github.dayal96.primitive.bool;

import io.github.dayal96.absyn.AbsynVisitor;
import io.github.dayal96.absyn.Absyn;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.Primitive;
import io.github.dayal96.primitive.PrimitiveVisitor;

public class MyBoolean extends Primitive implements Absyn {

  public static final MyBoolean TRUE = new MyBoolean(true);
  public static final MyBoolean FALSE = new MyBoolean(false);
  private final boolean value;
  /**
   * Create a Boolean value.
   *
   * @param value The boolean value, one of true and false.
   */
  private MyBoolean(boolean value) {
    this.value = value;
  }

  /**
   * Return the MyBoolean equivalent of the given boolean.
   *
   * @param toMimic The boolean whose equivalent has to be produced.
   * @return The MyBoolean that represents the boolean provided.
   */
  public static MyBoolean of(boolean toMimic) {
    return toMimic ? TRUE : FALSE;
  }

  @Override
  public IType getType() {
    return PrimType.BOOLEAN;
  }

  @Override
  public String toString() {
    return Boolean.toString(this.value);
  }

  /**
   * Determine the truth of this MyBoolean.
   *
   * @return True if this boolean is true, false otherwise.
   */
  public boolean truth() {
    return this.equals(TRUE);
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitBoolean(this);
  }

  @Override
  public <T> T accept(PrimitiveVisitor<T> visitor) {
    return visitor.visitMyBoolean(this);
  }
}
