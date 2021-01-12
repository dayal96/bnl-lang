package interpreter.evaluator;

import environment.IEnvironment;
import environment.SymbolTable;
import expression.IExpression;
import interpreter.IEvaluable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SimpleEvaluator implements IEvaluator {

  private final IEnvironment environment;

  /**
   * Create a SimpleEvaluator that has the given pre-defined expressions.
   *
   * @param predefined The pre-defined expressions for this evaluator.
   */
  public SimpleEvaluator(Map<String, IExpression> predefined) {
    this.environment = new SymbolTable();

    for (String key : predefined.keySet()) {
      this.environment.addEntry(key, predefined.get(key));
    }
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
