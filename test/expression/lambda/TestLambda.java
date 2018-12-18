package expression.lambda;

import environment.IEnvironment;
import environment.LocalContext;
import environment.SymbolTable;
import expression.Variable;
import expression.number.Rational;
import expression.operator.*;
import expression.operator.number.Multiply;
import expression.operator.number.Subtract;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestLambda {
    private final IEnvironment environment;

    public TestLambda() throws Exception {
        this.environment = new SymbolTable();

        this.environment.addEntry("ZERO", new Rational(0, 1));
        this.environment.addEntry("ONE", new Rational(1, 1));
        this.environment.addEntry("TWO", new Rational(2, 1));
        this.environment.addEntry("THREE", new Rational(3, 1));
        this.environment.addEntry("FOUR", new Rational(4, 1));
    }

    @Test
    public void testLambda() throws Exception {
        Lambda first = new Lambda(List.of("x", "y"), new Variable("x"));
        Lambda rest = new Lambda(List.of("x", "y"), new Variable("y"));
        Lambda apply = new Lambda(List.of("f"), new Lambda(List.of("x", "y"), new FunctionCall(new Variable("f"),
            List.of(new Variable("x"), new Variable("y")))));

        Variable one = new Variable("ONE");
        Variable two = new Variable("TWO");

        assertEquals(this.environment.getEntry("ONE"), first.evaluate(List.of(one, two), this.environment));
        assertEquals(this.environment.getEntry("TWO"), rest.evaluate(List.of(one, two), this.environment));
        assertEquals(this.environment.getEntry("ONE"), apply.evaluate(List.of(first), this.environment)
            .evaluate(List.of(one, two), this.environment));
        assertEquals(this.environment.getEntry("TWO"), apply.evaluate(List.of(rest), this.environment)
            .evaluate(List.of(one, two), this.environment));
    }

    @Test
    public void testRecursion() throws Exception {

        Variable x = new Variable("x");
        Variable zero = new Variable("ZERO");
        Variable one = new Variable("ONE");
        Variable two = new Variable("TWO");

        IEnvironment recursiveEnv = new LocalContext(this.environment, new SymbolTable());

        Lambda sizeNum = new Lambda(List.of("x"),
            new FunctionCall(new Conditional(),
                List.of(new FunctionCall(new Equals(), List.of(x, zero)),
                    new Rational(1, 1),
                    new FunctionCall(new Multiply(),
                        List.of(two, new FunctionCall(new Variable("size-num"),
                        List.of(new FunctionCall(new Subtract(), List.of(x, one)))))))));
        recursiveEnv.addEntry("size-num", sizeNum);

        System.out.println(sizeNum);
        System.out.println(sizeNum.evaluate(List.of(new Rational(30, 1)), recursiveEnv));
    }
}
