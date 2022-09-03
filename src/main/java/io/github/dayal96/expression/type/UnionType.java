package io.github.dayal96.expression.type;

/**
 * Represents a union of two types.
 */
public class UnionType extends NilType {
  private final IType type1, type2;

  /**
   * Create a union of the given two types.
   * @param type1 One of the types of the union.
   * @param type2 The other type of the union.
   */
  public UnionType(IType type1, IType type2) {
    this.type1 = type1;
    this.type2 = type2;
  }

  @Override
  public IType join(IType that) throws Exception {
    if (that instanceof UnionType) {
      UnionType other = (UnionType) that;
      try {
        return new UnionType(this.type1.join(other.type1), this.type2.join(other.type2));
      }
      catch (Exception e) {
        try {
          return new UnionType(this.type2.join(other.type1), this.type1.join(other.type2));
        }
        catch (Exception e2) {
          // Fall through
        }
      }
    }
    else if (that.equals(this.NIL)) {
      return this;
    }

    try {
      return this.type1.join(that);
    } catch (Exception e) {
      try {
        return this.type2.join(that);
      } catch (Exception f) {
        return this.mismatch(that);
      }
    }

  }

  @Override
  public String toString() {
    return ("(" + this.type1.toString() + ", " + this.type2.toString() + ")");
  }
}
