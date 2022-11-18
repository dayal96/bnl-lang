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
  @Override
  public Expression evaluate(List<Expression> operands, Environment environment) throws Exception {
    if (operands.size() != 1) {
      throw new Exception("list? : expected 1 argument, found " + operands.size());
    }
    Expression evaluated = operands.get(0).evaluate(environment);
    return evaluated.accept(new ListChecker());
  }

  @Override
  public String toString() {
    return "list?";
  }

  private static final class ListChecker implements ExpressionVisitor<MyBoolean> {

    @Override
    public MyBoolean visitConsPair(ConsPair expr) {
      return expr.rest.accept(this);
    }

    @Override
    public MyBoolean visitStruct(StructObject expr) {
      return MyBoolean.FALSE;
    }

    @Override
    public MyBoolean visitFunctionCall(FunctionCall expr) {
      return MyBoolean.FALSE;
    }

    @Override
    public MyBoolean visitLambda(Lambda expr) {
      return MyBoolean.FALSE;
    }

    @Override
    public MyBoolean visitLambdaEnclosure(LambdaEnclosure expr) {
      return MyBoolean.FALSE;
    }

    @Override
    public MyBoolean visitLocal(Local expr) {
      return MyBoolean.FALSE;
    }

    @Override
    public MyBoolean visitOperator(AOperator expr) {
      return MyBoolean.FALSE;
    }

    @Override
    public MyBoolean visitEmptyExpression(EmptyExpression expr) {
      return MyBoolean.FALSE;
    }

    @Override
    public MyBoolean visitVariable(Variable expr) {
      return MyBoolean.FALSE;
    }

    @Override
    public MyBoolean visitPrimitive(Primitive expr) {
      return MyBoolean.of(expr.equals(Empty.EMPTY));
    }
  }
}
