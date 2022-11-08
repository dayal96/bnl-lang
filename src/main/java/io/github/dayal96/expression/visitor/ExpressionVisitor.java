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

/**
 * Interface of Visitors for Expressions. Allows extending functionality for Expressions without
 * having to extend every subclass individually.
 * @param <T> The type of result produced by the visitor.
 */
public interface ExpressionVisitor<T> {
  T visitConsPair(ConsPair expr);

  T visitStruct(StructObject expr);

  T visitFunctionCall(FunctionCall expr);

  T visitLambda(Lambda expr);

  T visitLambdaEnclosure(LambdaEnclosure expr);

  T visitLocal(Local expr);

  T visitOperator(AOperator expr);

  T visitEmptyExpression(EmptyExpression expr);

  T visitVariable(Variable expr);

  T visitPrimitive(Primitive expr);
}
