package io.github.dayal96.interpreter.evaluator;

import io.github.dayal96.interpreter.IEvaluable;
import java.util.List;

public interface IEvaluator {

  void evaluateProgram(List<IEvaluable> toEval) throws Exception;
}
