package io.github.dayal96.absyn;

public class Decl implements IAbsyn {
  private String id;
  private IAbsyn expr;

  public Decl(String id, IAbsyn expr) {
    this.id = id;
    this.expr = expr;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitDecl(this.id, this.expr);
  }
}
