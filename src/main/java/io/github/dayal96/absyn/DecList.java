package io.github.dayal96.absyn;

import java.util.List;

public class DecList implements IAbsyn {
  private List<IAbsyn> declarations;

  public DecList(List<IAbsyn> declarations) {
    this.declarations = declarations;
  }

  public void addDecl(IAbsyn decl) {
    this.declarations.add(decl);
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitDecList(this.declarations);
  }
}
