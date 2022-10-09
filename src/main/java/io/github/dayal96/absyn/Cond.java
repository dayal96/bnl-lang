package io.github.dayal96.absyn;

public class Cond implements Absyn {
  private Absyn cond;
  private Absyn ifTrue;
  private Absyn ifFalse;

  public Cond(Absyn cond, Absyn ifTrue, Absyn ifFalse) {
    this.cond = cond;
    this.ifTrue = ifTrue;
    this.ifFalse = ifFalse;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitCond(this.cond, this.ifTrue, this.ifFalse);
  }
}
