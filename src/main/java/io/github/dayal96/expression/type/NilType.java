package io.github.dayal96.expression.type;

public class NilType implements IType {
  public static final IType NIL = new NilType();

  protected NilType() { }

  protected IType mismatch(IType that) throws Exception {
    throw new Exception("Type Mismatch : " + this + ", " + that);
  }

  @Override
  public IType join(IType that) throws Exception {
    return that;
  }
}
