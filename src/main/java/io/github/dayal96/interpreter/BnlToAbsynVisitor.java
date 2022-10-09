package io.github.dayal96.interpreter;

import io.github.dayal96.absyn.Cond;
import io.github.dayal96.absyn.DecList;
import io.github.dayal96.absyn.Decl;
import io.github.dayal96.absyn.ExprList;
import io.github.dayal96.absyn.FunCall;
import io.github.dayal96.absyn.Absyn;
import io.github.dayal96.absyn.Lambda;
import io.github.dayal96.absyn.LocalExpr;
import io.github.dayal96.absyn.Operator;
import io.github.dayal96.antlr.BnlParser.CondContext;
import io.github.dayal96.antlr.BnlParser.DeclContext;
import io.github.dayal96.antlr.BnlParser.DeclistContext;
import io.github.dayal96.antlr.BnlParser.DivideContext;
import io.github.dayal96.antlr.BnlParser.EqualsContext;
import io.github.dayal96.antlr.BnlParser.ExprContext;
import io.github.dayal96.antlr.BnlParser.ExprlistContext;
import io.github.dayal96.antlr.BnlParser.FuncallContext;
import io.github.dayal96.antlr.BnlParser.GeqContext;
import io.github.dayal96.antlr.BnlParser.GreaterThanContext;
import io.github.dayal96.antlr.BnlParser.IdlistContext;
import io.github.dayal96.antlr.BnlParser.LambdaContext;
import io.github.dayal96.antlr.BnlParser.LeqContext;
import io.github.dayal96.antlr.BnlParser.LessThanContext;
import io.github.dayal96.antlr.BnlParser.LocalexprContext;
import io.github.dayal96.antlr.BnlParser.MinusContext;
import io.github.dayal96.antlr.BnlParser.MultiplyContext;
import io.github.dayal96.antlr.BnlParser.PlusContext;
import io.github.dayal96.antlr.BnlParser.PrimFalseContext;
import io.github.dayal96.antlr.BnlParser.PrimIdContext;
import io.github.dayal96.antlr.BnlParser.PrimNumContext;
import io.github.dayal96.antlr.BnlParser.PrimStringContext;
import io.github.dayal96.antlr.BnlParser.PrimTrueContext;
import io.github.dayal96.antlr.BnlParser.ProgContext;
import io.github.dayal96.antlr.BnlParser.SimplexprContext;
import io.github.dayal96.antlr.BnlVisitor;
import io.github.dayal96.exceptions.ArithmeticError;
import io.github.dayal96.expression.Variable;
import io.github.dayal96.primitive.bool.MyBoolean;
import io.github.dayal96.primitive.number.Rational;
import io.github.dayal96.primitive.string.MyString;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class BnlToAbsynVisitor extends AbstractParseTreeVisitor<Absyn> implements
    BnlVisitor<Absyn> {

  @Override
  public Absyn visitProg(ProgContext ctx) {
    return this.visit(ctx.exprlist());
  }

  @Override
  public Absyn visitExprlist(ExprlistContext ctx) {
    if (Objects.isNull(ctx.exprlist())) {
      return new ExprList(new LinkedList<>(List.of(visitExpr(ctx.expr()))));
    } else {
      ExprList exprs = (ExprList) visit(ctx.exprlist());
      exprs.addExpr(visit(ctx.expr()));
      return exprs;
    }
  }

  @Override
  public Absyn visitExpr(ExprContext ctx) {
    if (Objects.nonNull(ctx.localexpr()) && !ctx.localexpr().isEmpty()) {
      return visit(ctx.localexpr());
    } else {
      return visit(ctx.simplexpr());
    }
  }

  @Override
  public Absyn visitLocalexpr(LocalexprContext ctx) {
    return new LocalExpr(visitDeclist(ctx.declist()), visitSimplexpr(ctx.simplexpr()));
  }

  @Override
  public Absyn visitDeclist(DeclistContext ctx) {
    if (Objects.nonNull(ctx.declist()) && !ctx.declist().isEmpty()) {
      DecList decList = (DecList) visitDeclist(ctx.declist());
      decList.addDecl(visitDecl(ctx.decl()));
      return decList;
    } else {
      return new DecList(new LinkedList<>(List.of(visitDecl(ctx.decl()))));
    }
  }

  @Override
  public Absyn visitDecl(DeclContext ctx) {
    return new Decl(ctx.ID().getSymbol().getText(), visitExpr(ctx.expr()));
  }

  @Override
  public Absyn visitSimplexpr(SimplexprContext ctx) {
    if (Objects.nonNull(ctx.prim()) && !ctx.prim().isEmpty()) {
      return this.visit(ctx.prim());
    } else if (Objects.nonNull(ctx.primop()) && !ctx.primop().isEmpty()) {
      return visit(ctx.primop());
    } else if (Objects.nonNull(ctx.cond()) && !ctx.cond().isEmpty()) {
      return visit(ctx.cond());
    } else if (Objects.nonNull(ctx.lambda()) && !ctx.lambda().isEmpty()) {
      return visit(ctx.lambda());
    } else {
      return visit(ctx.funcall());
    }
  }

  @Override
  public Absyn visitPrimId(PrimIdContext ctx) {
    return new Variable(ctx.getText());
  }

  @Override
  public Absyn visitPrimNum(PrimNumContext ctx) {
    String number = ctx.getText();
    int denomStart = number.indexOf("/");

    try {
      if(denomStart >= 0) {
        int numerator = Integer.parseInt(number.substring(0, denomStart));
        int denominator = Integer.parseInt(number.substring(denomStart+1));
        return new Rational(numerator, denominator);
      }
      else {
        return new Rational(Integer.parseInt(number));
      }
    }
    catch (ArithmeticError e) {
      throw new RuntimeException("Not a valid number : { " + number + " }");
    }
  }

  @Override
  public Absyn visitPrimString(PrimStringContext ctx) {
    String stringRep = ctx.getText();

    if (stringRep.length() <= 2) {
      return new MyString("");
    }

    return new MyString(stringRep.substring(1, stringRep.length() - 1)); // Remove the quotes
  }

  @Override
  public Absyn visitPrimFalse(PrimFalseContext ctx) {
    return MyBoolean.FALSE;
  }

  @Override
  public Absyn visitPrimTrue(PrimTrueContext ctx) {
    return MyBoolean.TRUE;
  }

  @Override
  public Absyn visitCond(CondContext ctx) {
    Absyn cond = this.visit(ctx.expr(0));
    Absyn ifTrue = this.visit(ctx.expr(1));
    Absyn ifFalse = this.visit(ctx.expr(2));
    return new Cond(cond, ifTrue, ifFalse);
  }

  @Override
  public Absyn visitLambda(LambdaContext ctx) {
    List<String> idList = this.readIdList(ctx.idlist());
    Absyn body = this.visit(ctx.expr());
    return new Lambda(idList, body);
  }

  private List<String> readIdList(IdlistContext ctx) {
    if (Objects.isNull(ctx.idlist())) {
      List<String> idList = new LinkedList<>();
      idList.add(ctx.ID().getText());
      return idList;
    } else {
      List<String> idList = readIdList(ctx.idlist());
      idList.add(ctx.ID().getText());
      return idList;
    }
  }

  @Override
  public Absyn visitIdlist(IdlistContext ctx) {
    throw new RuntimeException("idlist not supported as AST object.");
  }

  @Override
  public Absyn visitFuncall(FuncallContext ctx) {
    return new FunCall(this.visit(ctx.expr()), this.visit(ctx.exprlist()));
  }

  @Override
  public Absyn visitPlus(PlusContext ctx) {
    return Operator.PLUS;
  }

  @Override
  public Absyn visitMinus(MinusContext ctx) {
    return Operator.MINUS;
  }

  @Override
  public Absyn visitMultiply(MultiplyContext ctx) {
    return Operator.MULTIPLY;
  }

  @Override
  public Absyn visitDivide(DivideContext ctx) {
    return Operator.DIVIDE;
  }

  @Override
  public Absyn visitEquals(EqualsContext ctx) {
    return Operator.EQUALS;
  }

  @Override
  public Absyn visitLessThan(LessThanContext ctx) {
    return Operator.LT;
  }

  @Override
  public Absyn visitGreaterThan(GreaterThanContext ctx) {
    return Operator.GT;
  }

  @Override
  public Absyn visitLeq(LeqContext ctx) {
    return Operator.LEQ;
  }

  @Override
  public Absyn visitGeq(GeqContext ctx) {
    return Operator.GEQ;
  }
}
