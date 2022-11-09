package io.github.dayal96.expression.type;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the type of structure with given fields.
 */
public class StructType extends NilType {

  public final String name;
  public final List<IType> fieldTypes;
  public final List<String> fields;

  /**
   * Create a structure type with the given field types in order.
   * @param fields  The types of the fields of the structure, in order.
   */
  public StructType(String name, List<IType> fieldTypes, List<String> fields) {
    this.name = name;
    this.fieldTypes = fieldTypes;
    this.fields = fields;
  }

  @Override
  public IType join(IType that) throws Exception {
    if (that instanceof StructType other) {
      if (this.fields.size() != other.fields.size() || !Objects.equals(this.name, other.name)) {
        return this.mismatch(that);
      }
      List<IType> joinedFields = new LinkedList<>();
      try {
        boolean fieldsMatch = true;
        var f1iter = this.fields.iterator();
        var t1iter = this.fieldTypes.iterator();
        var f2iter = other.fields.iterator();
        var t2iter = other.fieldTypes.iterator();

        while (fieldsMatch && f1iter.hasNext() && t1iter.hasNext()
            && f2iter.hasNext() && t2iter.hasNext()) {
          fieldsMatch = Objects.equals(f1iter.next(), f2iter.next());
          joinedFields.add(t1iter.next().join(t2iter.next()));
        }
      }
      catch (Exception e) {
        return this.mismatch(that);
      }
      return new StructType(this.name, joinedFields, fields);
    }
    else if (that.equals(NIL)) {
      return this;
    }
    else {
      return this.mismatch(that);
    }
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof StructType that) {
      boolean match = Objects.equals(this.name, that.name)
          && this.fields.size() == that.fields.size();

      var it1 = this.fields.iterator();
      var it2 = that.fields.iterator();
      while (match && it1.hasNext() && it2.hasNext()) {
        match = Objects.equals(it1.next(), it2.next());
      }
      return match;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.fields.size());
  }
}
