package io.github.dayal96.absyn;

public class LocalExpr implements Absyn {

  private Absyn decList;
  private Absyn expr;


  public LocalExpr(Absyn decList, Absyn expr) {
    this.decList = decList;
    this.expr = expr;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitLocalExpr(this.decList, this.expr);
  }
}
