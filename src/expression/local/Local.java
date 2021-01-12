package expression.local;

import environment.IEnvironment;
import environment.LocalContext;
import environment.SymbolTable;
import expression.IExpression;
import expression.type.Type;
import java.util.List;

public class Local implements IExpression {

  private final List<LocalDefinition> localDefinitions;
  private final IExpression body;

  /**
   * Create a Local expression with given context that evaluates given expression.
   *
   * @param localDefinitions The context bound within this local expression.
   * @param body             The expression that would be evaluated within the local context.
   */
  public Local(List<LocalDefinition> localDefinitions, IExpression body) {
    this.localDefinitions = localDefinitions;
    this.body = body;
  }

  @Override
  public IExpression evaluate(IEnvironment environment) throws Exception {
    return this.body.evaluate(this.addLocalDefinitions(environment));
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    return this.body.evaluate(operands, this.addLocalDefinitions(environment));
  }

  private IEnvironment addLocalDefinitions(IEnvironment environment) throws Exception {
    SymbolTable localContext = new SymbolTable();
    IEnvironment fullContext = new LocalContext(environment, localContext);

    for (LocalDefinition def : this.localDefinitions) {
      localContext.addEntry(def.name, def.value.evaluate(localContext));
    }

    return fullContext;
  }

  @Override
  public Type getType() {
    return this.body.getType();
  }

  @Override
  public String toString() {
    return "(local " + this.localDefinitions.toString() + "; " + this.body.toString() + ")";
  }
}
