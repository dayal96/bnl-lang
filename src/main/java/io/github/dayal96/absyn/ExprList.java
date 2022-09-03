package io.github.dayal96.absyn;

import java.util.List;

/**
 * Represents the raw AST derived from code, an intermediate form to manipulate the same and
 * apply transforms before turning it into expressions.
 */
public class ExprList implements IAbsyn {

  private List<IAbsyn> exprs;

  public ExprList(List<IAbsyn> exprs) {
    this.exprs = exprs;
  }

  public void addExpr(IAbsyn expr) {
    this.exprs.add(expr);
  }

  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitExprList(this.exprs);
  }
}
