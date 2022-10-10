package io.github.dayal96.expression.type;

/**
 * All types stored here.
 */
public enum PrimType implements IType {
  NUMBER("NUMBER"), BOOLEAN("BOOLEAN"), STRING("STRING");

  private final String stringrep;

  PrimType(String stringrep) {
    this.stringrep = stringrep;
  }

  @Override
  public IType join(IType that) throws Exception {
    if (that instanceof PrimType) {
      if(this.equals(that)) {
        return this;
      }
      else {
        throw new Exception("Type mismatch : " + this + " , " + that);
      }
    }
    else {
      return that.join(this);
    }
  }

  @Override
  public String toString() {
    return this.stringrep;
  }
}
