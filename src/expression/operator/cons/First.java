package expression.operator.cons;

import environment.IEnvironment;
import expression.IExpression;
import expression.cons.ConsPair;
import expression.operator.AOperator;
import expression.type.Type;
import java.util.List;

public class First extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    if (operands.size() != 1) {
      throw new Exception("first : expected 1 argument, found " + operands.size());
    }

    IExpression evaluated = operands.get(0).evaluate(environment);

    if (!evaluated.getType().equals(Type.CONS_PAIR)) {
      throw new Exception("Expected a cons-pair, found " + evaluated);
    }

    return ((ConsPair) evaluated).first.evaluate(environment);
  }

  @Override
  public Type getType() {
    return Type.VARIABLE;
  }

  @Override
  public String toString() {
    return "first";
  }
}
