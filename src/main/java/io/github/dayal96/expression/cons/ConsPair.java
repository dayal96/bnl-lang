package io.github.dayal96.expression.cons;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.type.StructType;
import io.github.dayal96.expression.visitor.ExpressionVisitor;
import java.util.Arrays;
import java.util.List;

public class ConsPair implements Expression {

  public static final IType CONS_PAIR_TYPE = new StructType(Arrays.asList(NilType.NIL,
      NilType.NIL));

  public final Expression first;
  public final Expression rest;

  /**
   * Create a cons pair of two things.
   *
   * @param first The first part of the pair.
   * @param rest  The rest of the pair.
   */
  public ConsPair(Expression first, Expression rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public Expression evaluate(Environment environment) throws Exception {
    return new ConsPair(first.evaluate(environment), rest.evaluate(environment));
  }

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    throw new Exception("Cons pairs can not be used as functions.");
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visitConsPair(this);
  }

  @Override
  public IType getType() {
    return CONS_PAIR_TYPE;
  }

  @Override
  public String toString() {
    return "(cons " + first.toString() + " " + rest.toString() + ")";
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof ConsPair that) {
      return this.first.equals(that.first) && this.rest.equals(that.rest);
    }

    return false;
  }
}
