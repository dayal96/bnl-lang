package io.github.dayal96.absyn;

public class Decl implements Absyn {
  private String id;
  private Absyn expr;

  public Decl(String id, Absyn expr) {
    this.id = id;
    this.expr = expr;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitDecl(this.id, this.expr);
  }
}
