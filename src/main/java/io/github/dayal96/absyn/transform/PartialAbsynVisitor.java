package io.github.dayal96.absyn.transform;

import io.github.dayal96.absyn.AbsynVisitor;
import io.github.dayal96.absyn.Absyn;
import io.github.dayal96.absyn.Operator;
import io.github.dayal96.expression.Variable;
import io.github.dayal96.primitive.Primitive;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;

public abstract class PartialAbsynVisitor<T> implements AbsynVisitor<T> {

  protected RuntimeException message;

  protected PartialAbsynVisitor(RuntimeException message) {
    this.message = message;
  }

  @Override
  public T visitCond(Absyn cond, Absyn ifTrue, Absyn ifFalse) {
    throw this.message;
  }

  @Override
  public T visitDecl(String id, Absyn expr) {
    throw this.message;
  }

  @Override
  public T visitDecList(List<Absyn> decList) {
    throw this.message;
  }

  @Override
  public T visitExprList(List<Absyn> exprList) {
    throw this.message;
  }

  @Override
  public T visitFunCall(Absyn func, Absyn args) {
    throw this.message;
  }

  @Override
  public T visitLambda(List<String> idList, Absyn body) {
    throw this.message;
  }

  @Override
  public T visitLocalExpr(Absyn decList, Absyn expr) {
    throw this.message;
  }

  @Override
  public T visitOperator(Operator operator) {
    throw this.message;
  }

  @Override
  public T visitVariable(Variable variable) {
    throw this.message;
  }

  @Override
  public T visitPrimitive(Primitive primitive) {
    throw this.message;
  }

  @Override
  public T visitBoolean(MyBoolean bool) {
    throw this.message;
  }
}
