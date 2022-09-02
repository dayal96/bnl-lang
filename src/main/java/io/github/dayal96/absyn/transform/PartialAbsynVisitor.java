package io.github.dayal96.absyn.transform;

import io.github.dayal96.absyn.AbsynVisitor;
import io.github.dayal96.absyn.IAbsyn;
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
  public T visitCond(IAbsyn cond, IAbsyn ifTrue, IAbsyn ifFalse) {
    throw this.message;
  }

  @Override
  public T visitDecl(String id, IAbsyn expr) {
    throw this.message;
  }

  @Override
  public T visitDecList(List<IAbsyn> decList) {
    throw this.message;
  }

  @Override
  public T visitExprList(List<IAbsyn> exprList) {
    throw this.message;
  }

  @Override
  public T visitFunCall(IAbsyn func, IAbsyn args) {
    throw this.message;
  }

  @Override
  public T visitLambda(List<String> idList, IAbsyn body) {
    throw this.message;
  }

  @Override
  public T visitLocalExpr(IAbsyn decList, IAbsyn expr) {
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
