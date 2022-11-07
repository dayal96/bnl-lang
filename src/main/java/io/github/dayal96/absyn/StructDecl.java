package io.github.dayal96.absyn;

import java.util.List;

public class StructDecl implements Absyn {
  private final String name;
  private final List<String> idList;

  public StructDecl(String name, List<String> idList) {
    this.name = name;
    this.idList = idList;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitStructDecl(this.name, this.idList);
  }
}
