package expression.lambda;

import environment.IEnvironment;
import environment.LocalContext;
import environment.SymbolTable;
import expression.IExpression;
import expression.type.Type;

import java.util.List;

public class Lambda implements IExpression {
  private final List<String> inputs;
  private final IExpression body;

  /**
   * Create a Lambda Expression that evaluates an expression body based on some inputs.
   * @param inputs  The variables used in the function as input.
   * @param body    The expression that composes the body of the function.
   */
  public Lambda(List<String> inputs, IExpression body) {
    this.inputs = inputs;
    this.body = body;
  }

  @Override
  public IExpression evaluate(IEnvironment environment) throws Exception {
    if (environment.hasLocal()) {
      return new Local(environment.getLocal(), this);
    }
    else {
      return this;
    }
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment) throws Exception {

    if (operands.size() != this.inputs.size()) {
      throw new Exception("Expected " + this.inputs.size() + " arguments, received " + operands.size());
    }

    SymbolTable localBindings = new SymbolTable();

    for (int i = 0; i < this.inputs.size(); i++) {
      localBindings.addEntry(this.inputs.get(i), operands.get(i).evaluate(environment));
    }

    IEnvironment localContext = new LocalContext(environment, localBindings);

    return this.body.evaluate(localContext);
  }

  @Override
  public Type getType() {
    return Type.FUNCTION;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();

    str.append("(λ ( ");

    for (String input : this.inputs) {
      str.append(input + " ");
    }

    str.append(") " + this.body.toString() + ")");

    return str.toString();
  }
}
