package io.github.dayal96.expression;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.interpreter.IEvaluable;
import io.github.dayal96.interpreter.evaluator.IEvaluator;
import io.github.dayal96.expression.IExpression;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

public class TestEvaluator implements IEvaluator<Void> {
  private final IEnvironment environment;
  private final PrintWriter out;

  /**
   * Create a TestEvaluator that has the given pre-defined expressions and prints output to given
   * {@link Writer}.
   *
   * @param predefined  The pre-defined expressions for this evaluator.
   * @param out         The writer to write output to.
   */
  public TestEvaluator(SymbolTable predefined, Writer out) {
    this.environment = predefined;
    this.out = new PrintWriter(out);
  }

  @Override
  public Void evaluateProgram(List<IEvaluable> toEval) throws Exception {
    for (IEvaluable eval : toEval) {
      Optional<IExpression> result = eval.evaluate(this.environment);
      if (result.isPresent()) {
        out.println(result.get());
      }
    }
    return null;
  }
}
