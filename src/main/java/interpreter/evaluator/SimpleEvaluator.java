package interpreter.evaluator;

import static environment.SymbolTable.primitiveOperations;

import environment.IEnvironment;
import environment.SymbolTable;
import expression.IExpression;
import interpreter.IEvaluable;
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
