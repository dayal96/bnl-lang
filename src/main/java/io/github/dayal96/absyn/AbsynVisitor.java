package io.github.dayal96.absyn;

import io.github.dayal96.expression.Variable;
import io.github.dayal96.primitive.Primitive;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;

public interface AbsynVisitor<T> {

  T visitCond(Absyn cond, Absyn ifTrue, Absyn ifFalse);

  T visitDecl(String id, Absyn expr);

  T visitStructDecl(String name, List<String> idList);

  T visitDecList(List<Absyn> decList);

  T visitExprList(List<Absyn> exprList);

  T visitFunCall(Absyn func, Absyn args);

  T visitLambda(List<String> idList, Absyn body);

  T visitLocalExpr(Absyn decList, Absyn expr);

  T visitOperator(Operator operator);

  T visitVariable(Variable v);

  T visitPrimitive(Primitive primitive);

  T visitBoolean(MyBoolean bool);
}
