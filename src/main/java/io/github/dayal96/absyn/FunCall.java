package io.github.dayal96.absyn;

public class FunCall implements Absyn {

  private Absyn func;
  private Absyn args;

  public FunCall(Absyn func, Absyn args) {
    this.func = func;
    this.args = args;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitFunCall(this.func, this.args);
  }
}
