package io.github.dayal96.absyn;

public class Cond implements IAbsyn {
  private IAbsyn cond;
  private IAbsyn ifTrue;
  private IAbsyn ifFalse;

  public Cond(IAbsyn cond, IAbsyn ifTrue, IAbsyn ifFalse) {
    this.cond = cond;
    this.ifTrue = ifTrue;
    this.ifFalse = ifFalse;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitCond(this.cond, this.ifTrue, this.ifFalse);
  }
}
