package io.github.dayal96.absyn;

public class LocalExpr implements IAbsyn {

  private IAbsyn decList;
  private IAbsyn expr;


  public LocalExpr(IAbsyn decList, IAbsyn expr) {
    this.decList = decList;
    this.expr = expr;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitLocalExpr(this.decList, this.expr);
  }
}
