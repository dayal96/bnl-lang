package io.github.dayal96.expression.visitor;

import io.github.dayal96.expression.EmptyExpression;
import io.github.dayal96.expression.Variable;
import io.github.dayal96.expression.cons.ConsPair;
import io.github.dayal96.expression.lambda.FunctionCall;
import io.github.dayal96.expression.lambda.Lambda;
import io.github.dayal96.expression.lambda.LambdaEnclosure;
import io.github.dayal96.expression.local.Local;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.struct.StructObject;
import io.github.dayal96.primitive.Primitive;

public abstract class PartialExpressionVisitor<T> implements ExpressionVisitor<T> {

  protected final String message;

  protected PartialExpressionVisitor(String message) {
    this.message = message;
  }

  @Override
  public T visitConsPair(ConsPair expr) {
    throw new UnsupportedOperationException(message);
  }

  @Override
  public T visitStruct(StructObject expr) {
    throw new UnsupportedOperationException(message);
  }

  @Override
  public T visitFunctionCall(FunctionCall expr) {
    throw new UnsupportedOperationException(message);
  }

  @Override
  public T visitLambda(Lambda expr) {
    throw new UnsupportedOperationException(message);
  }

  @Override
  public T visitLambdaEnclosure(LambdaEnclosure expr) {
    throw new UnsupportedOperationException(message);
  }

  @Override
  public T visitLocal(Local expr) {
    throw new UnsupportedOperationException(message);
  }

  @Override
  public T visitOperator(AOperator expr) {
    throw new UnsupportedOperationException(message);
  }

  @Override
  public T visitEmptyExpression(EmptyExpression expr) {
    throw new UnsupportedOperationException(message);
  }

  @Override
  public T visitVariable(Variable expr) {
    throw new UnsupportedOperationException(message);
  }

  @Override
  public T visitPrimitive(Primitive expr) {
    throw new UnsupportedOperationException(message);
  }
}
