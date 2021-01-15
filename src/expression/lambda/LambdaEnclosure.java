package expression.lambda;

import environment.IEnvironment;
import environment.LocalContext;
import environment.SymbolTable;
import expression.IExpression;
import expression.type.IType;
import expression.type.NilType;
import java.util.List;

/**
 * Class to represent a lambda expression that closes over it's definition environment.
 * <p>
 * A Function Expression when evaluated closes on the environment it was defined in. In other words,
 * the context of a function is determined by it's definition and not invocation. So if a function
 * is defined in one scope but invoked from a different scope, it remembers the environment it was
 * defined in and uses that for lookups.
 */
public class LambdaEnclosure implements IExpression {

  private final List<String> inputs;
  private final IExpression body;
  private final IEnvironment context;

  /**
   * Create an enclosure for a function defined in given environment.
   *
   * @param inputs  The variables used in the function as input.
   * @param body    The body of the function.
   * @param context The environment the function is defined in.
   */
  public LambdaEnclosure(List<String> inputs, IExpression body, IEnvironment context) {
    this.inputs = inputs;
    this.body = body;
    this.context = context;
  }

  @Override
  public IExpression evaluate(IEnvironment environment) throws Exception {
    return this;
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {

    if (operands.size() != this.inputs.size()) {
      throw new Exception("Expected " + this.inputs.size() + " arguments, received "
          + operands.size());
    }

    SymbolTable localBindings = new SymbolTable();

    for (int i = 0; i < this.inputs.size(); i++) {
      localBindings.addEntry(this.inputs.get(i), operands.get(i).evaluate(environment));
    }

    IEnvironment localContext = new LocalContext(context, localBindings);

    return this.body.evaluate(localContext);
  }

  @Override
  public IType getType() {
    return NilType.NIL;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("(λ ( ");

    for (String input : this.inputs) {
      str.append(input).append(" ");
    }

    str.append(") ").append(this.body.toString()).append(")");
    return str.toString();
  }
}
