package io.github.dayal96.expression.operator;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.EmptyExpression;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.Variable;
import io.github.dayal96.expression.cons.ConsPair;
import io.github.dayal96.expression.lambda.FunctionCall;
import io.github.dayal96.expression.lambda.Lambda;
import io.github.dayal96.expression.lambda.LambdaEnclosure;
import io.github.dayal96.expression.local.Local;
import io.github.dayal96.expression.struct.StructObject;
import io.github.dayal96.expression.visitor.ExpressionVisitor;
import io.github.dayal96.primitive.Empty;
import io.github.dayal96.primitive.Primitive;
import io.github.dayal96.primitive.bool.MyBoolean;
import java.util.List;

public class IsList extends AOperator {

  public static final ListChecker LIST_CHECKER = new ListChecker();

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment) throws Exception {
    if (operands.size() != 1) {
      throw new Exception("list? : expected 1 argument, found " + operands.size());
    }
    Expression evaluated = operands.get(0).evaluate(environment);
    return MyBoolean.of(evaluated.accept(new ListChecker()));
  }

  @Override
  public String toString() {
    return "list?";
  }

  private static final class ListChecker implements ExpressionVisitor<Boolean> {

    private ListChecker() {}

    @Override
    public Boolean visitConsPair(ConsPair expr) {
      return expr.rest.accept(this);
    }

    @Override
    public Boolean visitStruct(StructObject expr) {
      return false;
    }

    @Override
    public Boolean visitFunctionCall(FunctionCall expr) {
      return false;
    }

    @Override
    public Boolean visitLambda(Lambda expr) {
      return false;
    }

    @Override
    public Boolean visitLambdaEnclosure(LambdaEnclosure expr) {
      return false;
    }

    @Override
    public Boolean visitLocal(Local expr) {
      return false;
    }

    @Override
    public Boolean visitOperator(AOperator expr) {
      return false;
    }

    @Override
    public Boolean visitEmptyExpression(EmptyExpression expr) {
      return false;
    }

    @Override
    public Boolean visitVariable(Variable expr) {
      return false;
    }

    @Override
    public Boolean visitPrimitive(Primitive expr) {
      return expr.equals(Empty.EMPTY);
    }
  }
}
