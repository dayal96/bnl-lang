package io.github.dayal96.interpreter.evaluator;

import io.github.dayal96.interpreter.IEvaluable;
import java.util.List;

public interface IEvaluator<T> {

  T evaluateProgram(List<IEvaluable> toEval) throws Exception;
}
