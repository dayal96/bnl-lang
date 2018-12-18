package expression.operator.number;

import java.util.LinkedList;
import java.util.List;

import environment.IEnvironment;
import exceptions.ArithmeticError;
import expression.IExpression;
import expression.bool.MyBoolean;
import expression.number.MyNumber;
import expression.operator.AOperator;
import expression.type.Type;

/**
 * Class to represent Addition.
 */
public class LessThan extends AOperator {

    @Override
    public IExpression evaluate(List<IExpression> operands, IEnvironment environment) throws Exception {

        boolean allNumbers = true;
        List<IExpression> eval = new LinkedList<>();

        for (IExpression e : operands) {
            IExpression evaluated = e.evaluate(environment);
            eval.add(evaluated);
            allNumbers = allNumbers && (evaluated.getType().equals(Type.NUMBER));
        }

        if (!allNumbers) {
            throw new IllegalArgumentException("All operands must be numbers.");
        }
        else if (eval.size() >= 2) {
            boolean result = true;

            MyNumber prev = (MyNumber)eval.get(0);
            MyNumber current = (MyNumber)eval.get(0);

            for (int i = 1; i < eval.size(); i++) {
                current = (MyNumber)eval.get(i);
                result = result && (prev.compareTo(current) < 0);
                prev = current;
            }

            return MyBoolean.of(result);
        }
        else {
            throw new IllegalArgumentException("Too few arguments for IOperator.");
        }
    }

    @Override
    public Type getType() {
        return Type.NUMBER;
    }

    @Override
    public String toString() {
        return "<";
    }
}
