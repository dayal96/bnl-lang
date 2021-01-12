package expression;

import environment.IEnvironment;
import environment.SymbolTable;
import interpreter.IEvaluable;
import interpreter.evaluator.IEvaluator;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

public class TestEvaluator implements IEvaluator {
  private final IEnvironment environment;
  private final PrintWriter out;

  /**
   * Create a TestEvaluator that has the given pre-defined expressions and prints output to given
   * {@link Writer}.
   *
   * @param predefined The pre-defined expressions for this evaluator.
   */
  public TestEvaluator(SymbolTable predefined, Writer out) {
    this.environment = predefined;
    this.out = new PrintWriter(out);
  }

  @Override
  public void evaluateProgram(List<IEvaluable> toEval) throws Exception {
    for (IEvaluable eval : toEval) {
      Optional<IExpression> result = eval.evaluate(this.environment);
      if (result.isPresent()) {
        out.println(result.get());
      }
    }
  }
}
