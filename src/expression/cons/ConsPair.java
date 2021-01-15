package expression.cons;

import environment.IEnvironment;
import expression.IExpression;
import expression.type.IType;
import expression.type.NilType;
import expression.type.PrimType;
import expression.type.StructType;
import java.util.Arrays;
import java.util.List;

public class ConsPair implements IExpression {

  public static final IType CONS_PAIR_TYPE = new StructType(Arrays.asList(NilType.NIL,
      NilType.NIL));

  public final IExpression first;
  public final IExpression rest;

  /**
   * Create a cons pair of two things.
   *
   * @param first The first part of the pair.
   * @param rest  The rest of the pair.
   */
  public ConsPair(IExpression first, IExpression rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public IExpression evaluate(IEnvironment environment) throws Exception {
    return new ConsPair(first.evaluate(environment), rest.evaluate(environment));
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    throw new Exception("Cons pairs can not be used as functions.");
  }

  @Override
  public IType getType() {
    return this.CONS_PAIR_TYPE;
  }

  @Override
  public String toString() {
    return "(cons " + first.toString() + " " + rest.toString() + ")";
  }
}
