package io.github.dayal96.interpreter.evaluator;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.interpreter.Evaluable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

public class SimpleEvaluator implements Evaluator<Void> {

  private final Environment environment;
  private final Writer out;

  /**
   * Create a SimpleEvaluator that has the given pre-defined expressions.
   *
   * @param predefined The pre-defined expressions for this evaluator.
   */
  public SimpleEvaluator(Environment predefined, Writer out) {
    this.environment = predefined;
    this.out = out;
  }

  public SimpleEvaluator() {
    this(SymbolTable.getPrimitiveOperations(), new OutputStreamWriter(System.out));
  }

  @Override
  public Void evaluateProgram(List<Evaluable> toEval) throws Exception {
    for (Evaluable eval : toEval) {
      Optional<Expression> result = eval.evaluate(this.environment);
      result.ifPresent((toPrint) -> {
        try {
          out.write(toPrint + "\n");
        } catch (IOException ignored) {}
      });
    }
    out.flush();
    return null;
  }
}
