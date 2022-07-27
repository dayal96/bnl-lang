package io.github.dayal96.interpreter.evaluator;

import static io.github.dayal96.environment.SymbolTable.primitiveOperations;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.interpreter.IEvaluable;
import java.util.List;
import java.util.Optional;

public class SimpleEvaluator implements IEvaluator {



  private final IEnvironment environment;

  /**
   * Create a SimpleEvaluator that has the given pre-defined expressions.
   *
   * @param predefined The pre-defined expressions for this evaluator.
   */
  public SimpleEvaluator(SymbolTable predefined) {
    this.environment = predefined;
  }

  public SimpleEvaluator() {
    this(primitiveOperations);
  }

  @Override
  public void evaluateProgram(List<IEvaluable> toEval) throws Exception {
    for (IEvaluable eval : toEval) {
      Optional<IExpression> result = eval.evaluate(this.environment);
      if (result.isPresent()) {
        System.out.println(result.get());
      }
    }
  }
}
