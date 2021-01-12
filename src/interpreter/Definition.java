package interpreter;

import environment.IEnvironment;
import expression.IExpression;
import java.util.Optional;

public class Definition implements IEvaluable {

  private final String name;
  private final IExpression definition;

  /**
   * Create a Definition which when evaluated, will add a definition to the environment.
   *
   * @param name       The identifier assigned to the definition.
   * @param definition The definition itself.
   */
  public Definition(String name, IExpression definition) {
    this.name = name;
    this.definition = definition;
  }


  @Override
  public Optional<IExpression> evaluate(IEnvironment environment) throws Exception {

    if (environment.isPresent(this.name)) {
      throw new Exception(this.name + " has already been defined.");
    }

    environment.addEntry(this.name, this.definition.evaluate(environment));
    return Optional.empty();
  }

  @Override
  public String toString() {
    return "(define " + this.name + " " + this.definition.toString() + ")";
  }
}
