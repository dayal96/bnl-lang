package io.github.dayal96.primitive.string;

import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.Primitive;
import io.github.dayal96.primitive.PrimitiveVisitor;

/**
 * Class to represent a String.
 */
public class MyString extends Primitive implements Comparable<MyString> {

  public final String value;

  public MyString(String value) {
    this.value = value;
  }

  @Override
  public IType getType() {
    return PrimType.STRING;
  }

  @Override
  public int compareTo(MyString that) {
    return this.value.compareTo(that.value);
  }

  @Override
  public boolean equals(Object that) {
    return that instanceof MyString && this.value.equals(((MyString) that).value);
  }

  @Override
  public String toString() {
    return this.value;
  }

  @Override
  public <T> T accept(PrimitiveVisitor<T> visitor) {
    return visitor.visitMyString(this);
  }
}
