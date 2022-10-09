package io.github.dayal96.absyn.transform;

import io.github.dayal96.absyn.Absyn;
import io.github.dayal96.expression.Expression;
import java.util.List;
import java.util.stream.Collectors;

public class AbsynToExprList extends PartialAbsynVisitor<List<Expression>> {

  private static final AbsynToExprList instance = new AbsynToExprList();

  public static AbsynToExprList getInstance() {
    return instance;
  }

  private AbsynToExprList() {
    super(new UnsupportedOperationException("Input is not a list of expressions"));
  }

  @Override
  public List<Expression> visitExprList(List<Absyn> exprList) {
    return exprList.stream().map((expr) -> expr.accept(SimpleAbsynToExpr.getInstance()))
        .collect(Collectors.toList());
  }
}
