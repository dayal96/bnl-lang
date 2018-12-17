package expression.bool;

import environment.IEnvironment;
import expression.IExpression;
import expression.type.Type;

import java.util.List;

public class MyBoolean implements IExpression {

    private final boolean value;

    /**
     * Create a Boolean value.
     * @param value The boolean value, one of true and false.
     */
    private MyBoolean(boolean value) {
        this.value = value;
    }

    public static final MyBoolean TRUE = new MyBoolean(true);
    public static final MyBoolean FALSE = new MyBoolean(false);

    @Override
    public IExpression evaluate(IEnvironment environment) throws Exception {
        return this;
    }

    @Override
    public IExpression evaluate(List<IExpression> operands, IEnvironment environment) throws Exception {
        throw new Exception("Cannot evaluate a boolean like a function.");
    }

    @Override
    public Type getType() {
        return Type.BOOLEAN;
    }

    @Override
    public String toString() {
        return Boolean.toString(this.value);
    }
}
