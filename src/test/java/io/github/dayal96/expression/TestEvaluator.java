package io.github.dayal96.expression;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.interpreter.Evaluable;
import io.github.dayal96.interpreter.evaluator.Evaluator;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

public class TestEvaluator implements Evaluator<Void> {
  private final Environment environment;
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
  public Void evaluateProgram(List<Evaluable> toEval) throws Exception {
    for (Evaluable eval : toEval) {
      Optional<Expression> result = eval.evaluate(this.environment);
      if (result.isPresent()) {
        out.println(result.get());
      }
    }
    return null;
  }
}
