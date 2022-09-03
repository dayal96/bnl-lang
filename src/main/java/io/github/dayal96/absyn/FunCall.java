package io.github.dayal96.absyn;

public class FunCall implements IAbsyn {

  private IAbsyn func;
  private IAbsyn args;

  public FunCall(IAbsyn func, IAbsyn args) {
    this.func = func;
    this.args = args;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitFunCall(this.func, this.args);
  }
}
