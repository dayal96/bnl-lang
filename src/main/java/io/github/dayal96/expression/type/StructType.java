package io.github.dayal96.expression.type;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the type of a structure with given fields.
 */
public class StructType extends NilType {
  private final List<IType> fields;

  /**
   * Create a structure type with the given field types in order.
   * @param fields  The types of the fields of the structure, in order.
   */
  public StructType(List<IType> fields) {
    this.fields = fields;
  }


  @Override
  public IType join(IType that) throws Exception {
    if (that instanceof StructType other) {
      if (this.fields.size() != other.fields.size()) {
        return this.mismatch(that);
      }

      List<IType> joinedFields = new LinkedList<>();
      try {
        for (int i = 0; i < this.fields.size(); i++) {
          joinedFields.add(this.fields.get(i).join(other.fields.get(i)));
        }
      }
      catch (Exception e) {
        return this.mismatch(that);
      }

      return new StructType(joinedFields);
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
    StringBuilder str = new StringBuilder("(");
    for(IType input : this.fields) {
      str.append(" ").append(input);
    }
    str.append(" )");
    return str.toString();
  }
}
