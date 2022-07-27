package io.github.dayal96.expression.local;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.environment.LocalContext;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.EmptyExpression;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.type.IType;
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
    throw new Exception("This is impossible, so this message will never be seen.");
  }

  private IEnvironment addLocalDefinitions(IEnvironment environment) throws Exception {
    SymbolTable localContext = new SymbolTable();
    for (LocalDefinition def : this.localDefinitions) {
      localContext.addEntry(def.name, EmptyExpression.EMPTY_EXPR);
    }

    IEnvironment fullContext = new LocalContext(environment, localContext);
    for (LocalDefinition def : this.localDefinitions) {
      fullContext.addEntry(def.name, def.value.evaluate(fullContext));
    }

    return fullContext;
  }

  @Override
  public IType getType() {
    return this.body.getType();
  }

  @Override
  public String toString() {
    return "(local " + this.localDefinitions.toString() + "; " + this.body.toString() + ")";
  }
}
