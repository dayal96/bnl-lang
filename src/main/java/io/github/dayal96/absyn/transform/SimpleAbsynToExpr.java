package io.github.dayal96.absyn.transform;

import io.github.dayal96.absyn.AbsynVisitor;
import io.github.dayal96.absyn.IAbsyn;
import io.github.dayal96.absyn.Operator;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.Variable;
import io.github.dayal96.expression.lambda.FunctionCall;
import io.github.dayal96.expression.lambda.Lambda;
import io.github.dayal96.expression.local.Local;
import io.github.dayal96.primitive.Primitive;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;

public class SimpleAbsynToExpr implements AbsynVisitor<IExpression> {

  private static final SimpleAbsynToExpr instance =
      new SimpleAbsynToExpr();

  private SimpleAbsynToExpr() {}

  public static SimpleAbsynToExpr getInstance() { return instance; }

  @Override
  public IExpression visitCond(IAbsyn cond, IAbsyn ifTrue, IAbsyn ifFalse) {
    return new FunctionCall(new Variable("if"), List.of(cond.accept(this), ifTrue.accept(this),
        ifFalse.accept(this)));
  }

  @Override
  public IExpression visitDecl(String id, IAbsyn expr) {
    throw new RuntimeException("Declarations cannot become Expressions");
  }

  @Override
  public IExpression visitDecList(List<IAbsyn> decList) {
    throw new RuntimeException("Declarations cannot become Expressions");
  }

  @Override
  public IExpression visitExprList(List<IAbsyn> exprList) {
    throw new RuntimeException("Unexpected expression list while transforming");
  }

  @Override
  public IExpression visitFunCall(IAbsyn func, IAbsyn args) {
    return new FunctionCall(func.accept(this),
        args.accept(AbsynToExprList.getInstance()));
  }

  @Override
  public IExpression visitLambda(List<String> idList, IAbsyn body) {
    return new Lambda(idList, body.accept(this));
  }

  @Override
  public IExpression visitLocalExpr(IAbsyn decList, IAbsyn expr) {
    return new Local(decList.accept(DeclarationToLocalDef.getInstance()), expr.accept(this));
  }

  @Override
  public IExpression visitOperator(Operator operator) {
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
  public IExpression visitVariable(Variable v) {
    return v;
  }

  @Override
  public IExpression visitPrimitive(Primitive primitive) {
    return primitive;
  }

  @Override
  public IExpression visitBoolean(MyBoolean bool) {
    return bool;
  }
}
