package io.github.dayal96.absyn.transform;

import io.github.dayal96.absyn.AbsynVisitor;
import io.github.dayal96.absyn.Absyn;
import io.github.dayal96.absyn.Operator;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.Variable;
import io.github.dayal96.expression.lambda.FunctionCall;
import io.github.dayal96.expression.lambda.Lambda;
import io.github.dayal96.expression.local.Local;
import io.github.dayal96.primitive.Primitive;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;

public class SimpleAbsynToExpr implements AbsynVisitor<Expression> {

  private static final SimpleAbsynToExpr instance =
      new SimpleAbsynToExpr();

  private SimpleAbsynToExpr() {}

  public static SimpleAbsynToExpr getInstance() { return instance; }

  @Override
  public Expression visitCond(Absyn cond, Absyn ifTrue, Absyn ifFalse) {
    return new FunctionCall(new Variable("if"), List.of(cond.accept(this), ifTrue.accept(this),
        ifFalse.accept(this)));
  }

  @Override
  public Expression visitDecl(String id, Absyn expr) {
    throw new RuntimeException("Declarations cannot become Expressions");
  }

  @Override
  public Expression visitDecList(List<Absyn> decList) {
    throw new RuntimeException("Declarations cannot become Expressions");
  }

  @Override
  public Expression visitExprList(List<Absyn> exprList) {
    throw new RuntimeException("Unexpected expression list while transforming");
  }

  @Override
  public Expression visitFunCall(Absyn func, Absyn args) {
    return new FunctionCall(func.accept(this),
        args.accept(AbsynToExprList.getInstance()));
  }

  @Override
  public Expression visitLambda(List<String> idList, Absyn body) {
    return new Lambda(idList, body.accept(this));
  }

  @Override
  public Expression visitLocalExpr(Absyn decList, Absyn expr) {
    return new Local(decList.accept(DeclarationToLocalDef.getInstance()), expr.accept(this));
  }

  @Override
  public Expression visitOperator(Operator operator) {
    switch(operator) {
      case PLUS: return new Variable("+");
      case MINUS: return new Variable("-");
      case MULTIPLY: return new Variable("*");
      case DIVIDE: return new Variable("/");
      case EQUALS: return new Variable("=");
      case LT: return new Variable("<");
      case GT: return new Variable(">");
      case LEQ: return new Variable("<=");
      case GEQ: return new Variable(">=");
    }

    throw new RuntimeException("Unrecognised operator : " + operator);
  }

  @Override
  public Expression visitVariable(Variable v) {
    return v;
  }

  @Override
  public Expression visitPrimitive(Primitive primitive) {
    return primitive;
  }

  @Override
  public Expression visitBoolean(MyBoolean bool) {
    return bool;
  }
}
