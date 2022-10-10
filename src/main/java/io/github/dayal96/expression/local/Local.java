package io.github.dayal96.expression.local;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.LocalContext;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.EmptyExpression;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.visitor.ExpressionVisitor;
import java.util.List;

public class Local implements Expression {

  private final List<LocalDefinition> localDefinitions;
  private final Expression body;

  /**
   * Create a Local expression with given context that evaluates given expression.
   *
   * @param localDefinitions The context bound within this local expression.
   * @param body             The expression that would be evaluated within the local context.
   */
  public Local(List<LocalDefinition> localDefinitions, Expression body) {
    this.localDefinitions = localDefinitions;
    this.body = body;
  }

  @Override
  public Expression evaluate(Environment environment) throws Exception {
    return this.body.evaluate(this.addLocalDefinitions(environment));
  }

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment)
      throws Exception {
    throw new Exception("This is impossible, so this message will never be seen.");
  }

  @Override
  public <T> T accept(ExpressionVisitor<T> visitor) {
    return visitor.visitLocal(this);
  }

  private Environment addLocalDefinitions(Environment environment) throws Exception {
    SymbolTable localContext = new SymbolTable();
    for (LocalDefinition def : this.localDefinitions) {
      localContext.addEntry(def.name, EmptyExpression.EMPTY_EXPR);
    }

    Environment fullContext = new LocalContext(environment, localContext);
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
