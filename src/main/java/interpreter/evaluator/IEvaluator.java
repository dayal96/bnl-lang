package interpreter.evaluator;

import expression.IExpression;
import expression.operator.AOperator;
import expression.operator.Conditional;
import expression.operator.Equals;
import expression.operator.cons.Cons;
import expression.operator.cons.First;
import expression.operator.cons.Rest;
import expression.operator.number.Add;
import expression.operator.number.Divide;
import expression.operator.number.GreaterThan;
import expression.operator.number.LessThan;
import expression.operator.number.Multiply;
import expression.operator.number.Subtract;
import interpreter.IEvaluable;
import java.util.HashMap;
import java.util.List;

public interface IEvaluator {

  void evaluateProgram(List<IEvaluable> toEval) throws Exception;
}
