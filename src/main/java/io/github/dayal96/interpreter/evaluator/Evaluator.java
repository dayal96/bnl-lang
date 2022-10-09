package io.github.dayal96.interpreter.evaluator;

import io.github.dayal96.interpreter.Evaluable;
import java.util.List;

public interface Evaluator<T> {

  T evaluateProgram(List<Evaluable> toEval) throws Exception;
}
