package expression.operator.cons;

import environment.IEnvironment;
import expression.IExpression;
import expression.cons.ConsPair;
import expression.operator.AOperator;
import expression.type.IType;
import expression.type.NilType;
import expression.type.PrimType;
import java.util.List;

public class Rest extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    if (operands.size() != 1) {
      throw new Exception("rest : expected 1 argument, found " + operands.size());
    }

    IExpression evaluated = operands.get(0).evaluate(environment);

    if (!evaluated.getType().equals(ConsPair.CONS_PAIR_TYPE)) {
      throw new Exception("Expected a cons-pair, found " + evaluated);
    }

    return ((ConsPair) evaluated).rest.evaluate(environment);
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }

  @Override
  public String toString() {
    return "rest";
  }
}
