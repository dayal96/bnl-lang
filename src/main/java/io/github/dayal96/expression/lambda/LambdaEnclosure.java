package io.github.dayal96.expression.lambda;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.LocalContext;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import java.util.List;

/**
 * Class to represent a lambda expression that closes over its definition environment.
 * <p>
 * A Function Expression when evaluated closes on the environment it was defined in. In other words,
 * the context of a function is determined by its definition and not invocation. So if a function
 * is defined in one scope but invoked from a different scope, it remembers the environment it was
 * defined in and uses that for lookups.
 */
public class LambdaEnclosure implements Expression {

  private final List<String> inputs;
  private final Expression body;
  private final Environment context;

  /**
   * Create an enclosure for a function defined in given environment.
   *
   * @param inputs  The variables used in the function as input.
   * @param body    The body of the function.
   * @param context The environment the function is defined in.
   */
  public LambdaEnclosure(List<String> inputs, Expression body, Environment context) {
    this.inputs = inputs;
    this.body = body;
    this.context = context;
  }

  @Override
  public Expression evaluate(Environment environment) throws Exception {
    return this;
  }

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {

    if (operands.size() != this.inputs.size()) {
      throw new Exception("Expected " + this.inputs.size() + " arguments, received "
          + operands.size());
    }

    SymbolTable localBindings = new SymbolTable();

    for (int i = 0; i < this.inputs.size(); i++) {
      localBindings.addEntry(this.inputs.get(i), operands.get(i).evaluate(environment));
    }

    Environment localContext = new LocalContext(context, localBindings);

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
