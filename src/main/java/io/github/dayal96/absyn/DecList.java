package io.github.dayal96.absyn;

import java.util.List;

public class DecList implements Absyn {
  private List<Absyn> declarations;

  public DecList(List<Absyn> declarations) {
    this.declarations = declarations;
  }

  public void addDecl(Absyn decl) {
    this.declarations.add(decl);
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitDecList(this.declarations);
  }
}
