package io.github.dayal96.absyn;

import io.github.dayal96.expression.Variable;
import io.github.dayal96.primitive.Primitive;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;

public interface AbsynVisitor<T> {

  T visitCond(IAbsyn cond, IAbsyn ifTrue, IAbsyn ifFalse);

  T visitDecl(String id, IAbsyn expr);

  T visitDecList(List<IAbsyn> decList);

  T visitExprList(List<IAbsyn> exprList);

  T visitFunCall(IAbsyn func, IAbsyn args);

  T visitLambda(List<String> idList, IAbsyn body);

  T visitLocalExpr(IAbsyn decList, IAbsyn expr);

  T visitOperator(Operator operator);

  T visitVariable(Variable v);

  T visitPrimitive(Primitive primitive);

  T visitBoolean(MyBoolean bool);
}
