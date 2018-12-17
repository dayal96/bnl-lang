package expression.lambda;

import environment.IEnvironment;
import environment.LocalContext;
import expression.IExpression;
import expression.type.Type;

import java.util.List;

public class Local implements IExpression {

    private final IEnvironment localContext;
    private final IExpression body;

    /**
     * Create a Local expression with given context that evaluates given expression.
     * @param localContext  The context bound within this local expression.
     * @param body          The expression that would be evaluated within the local context.
     */
    public Local(IEnvironment localContext, IExpression body) {
        this.localContext = localContext;
        this.body = body;
    }

    @Override
    public IExpression evaluate(IEnvironment environment) throws Exception {
        return this.body.evaluate(new LocalContext(environment, this.localContext));
    }

    @Override
    public IExpression evaluate(List<IExpression> operands, IEnvironment environment) throws Exception {
        return this.body.evaluate(operands, new LocalContext(environment, this.localContext));
    }

    @Override
    public Type getType() {
        return this.body.getType();
    }

    @Override
    public String toString() {
        return "(local [" + this.localContext.toString() + "]; " + this.body.toString() + ")";
    }
}
