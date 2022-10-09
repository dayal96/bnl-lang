package io.github.dayal96.absyn;

public enum Operator implements Absyn {
  PLUS,
  MINUS,
  MULTIPLY,
  DIVIDE,
  EQUALS,
  LT,
  GT,
  LEQ,
  GEQ;


  @Override
  public <T> T accept(AbsynVisitor<T> visitor) {
    return visitor.visitOperator(this);
  }
}
