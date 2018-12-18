package expression.operator.number;

import environment.IEnvironment;
import exceptions.ArithmeticError;
import expression.IExpression;
import expression.number.MyNumber;
import expression.operator.AOperator;
import expression.type.Type;

import java.util.List;

public class Divide extends AOperator {

    @Override
    public IExpression evaluate(List<IExpression> operands, IEnvironment environment) throws Exception {

        boolean allNumbers = true;

        for (IExpression e : operands) {
            allNumbers = allNumbers && (e.getType().equals(Type.NUMBER));
        }

        if (!allNumbers) {
            throw new IllegalArgumentException("All operands must be numbers.");
        }
        else if (operands.size() == 1) {
            return operands.get(0).evaluate(environment);
        }
        else if (operands.size() > 1) {
            MyNumber result = (MyNumber)(operands.get(0).evaluate(environment));

            for (int i = 1; i < operands.size(); i++) {

                try {
                    result = result.divide((MyNumber) (operands.get(i).evaluate(environment)));
                }
                catch (ArithmeticError e) {
                    e.printStackTrace();
                }
            }

            return result;
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
        return "/";
    }
}
