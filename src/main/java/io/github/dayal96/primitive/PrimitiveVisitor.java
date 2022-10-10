package io.github.dayal96.primitive;

import io.github.dayal96.primitive.bool.MyBoolean;
import io.github.dayal96.primitive.number.MyNumber;
import io.github.dayal96.primitive.string.MyString;

/**
 * Interface of Visitors for Expressions. Allows extending functionality for Expressions without
 * having to extend every subclass individually.
 * @param <T> The type of result produced by the visitor.
 */
public interface PrimitiveVisitor<T> {

  T visitMyBoolean(MyBoolean prim);

  <N> T visitMyNumber(MyNumber<N> prim);

  T visitMyString(MyString prim);
}
