package io.github.dayal96.absyn;

import java.util.List;

public class Lambda implements Absyn {
  private List<String> idList;
  private Absyn body;

  public Lambda(List<String> idList, Absyn body) {
    this.idList = idList;
    this.body = body;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitLambda(this.idList, this.body);
  }
}
