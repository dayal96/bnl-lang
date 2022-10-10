package io.github.dayal96.absyn;

import java.util.List;

public class AbsynLambda implements Absyn {
  private final List<String> idList;
  private final Absyn body;

  public AbsynLambda(List<String> idList, Absyn body) {
    this.idList = idList;
    this.body = body;
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitLambda(this.idList, this.body);
  }
}
