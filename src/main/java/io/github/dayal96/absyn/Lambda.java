package io.github.dayal96.absyn;

import java.util.List;

public class Lambda implements IAbsyn {
  private List<String> idList;
  private IAbsyn body;

  public Lambda(List<String> idList, IAbsyn body) {
    this.idList = idList;
    this.body = body;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitLambda(this.idList, this.body);
  }
}
