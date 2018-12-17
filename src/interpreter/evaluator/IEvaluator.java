package interpreter.evaluator;

import interpreter.IEvaluable;

import java.util.List;

public interface IEvaluator {

  public void evaluateProgram(List<IEvaluable> toEval) throws Exception;
}
