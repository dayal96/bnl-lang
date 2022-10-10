package io.github.dayal96.expression;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.visitor.ExpressionVisitor;
import java.util.List;

/**
 * <p>Interface to represent a data value.</p>
 * <p>
 * A Expression is one of:
 *   <ul>
 *     <li>Primitive</li>
 *     <li>FunctionCall</li>
 *   </ul>
 * </p>
 */
public interface Expression {

  /**
   * Evaluate this Expression to the simplest form possible.
   *
   * @return the simplest form of the Expression.
   */
  Expression evaluate(Environment environment) throws Exception;

  /**
   * Evaluate the function with given input.
   *
   * @return the result of performing the operation on given values.
   */
  Expression evaluate(List<Expression> operands, Environment environment) throws Exception;

  /**
   * Accept a visitor.
   * @param visitor The visitor to accept.
   * @return        Result evaluated by the visitor.
   * @param <T>     The type of result evaluated by the visitor.
   */
  <T> T accept(ExpressionVisitor<T> visitor);

  /**
   * Get the type of this Expression.
   *
   * @return the String representing the type of this Expression.
   */
  IType getType();

  @Override
  String toString();
}
