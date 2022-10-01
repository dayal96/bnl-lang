package io.github.dayal96.interpreter.evaluator;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.interpreter.IEvaluable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

public class SimpleEvaluator implements IEvaluator<Void> {

  private final IEnvironment environment;
  private final Writer out;

  /**
   * Create a SimpleEvaluator that has the given pre-defined expressions.
   *
   * @param predefined The pre-defined expressions for this evaluator.
   */
  public SimpleEvaluator(IEnvironment predefined, Writer out) {
    this.environment = predefined;
    this.out = out;
  }

  public SimpleEvaluator() {
    this(SymbolTable.getPrimitiveOperations(), new OutputStreamWriter(System.out));
  }

  @Override
  public Void evaluateProgram(List<IEvaluable> toEval) throws Exception {
    for (IEvaluable eval : toEval) {
      Optional<IExpression> result = eval.evaluate(this.environment);
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
