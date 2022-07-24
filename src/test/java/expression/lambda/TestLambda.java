package expression.lambda;

import static org.junit.Assert.assertEquals;

import environment.IEnvironment;
import environment.LocalContext;
import environment.SymbolTable;
import expression.IExpression;
import expression.Variable;
import primitive.number.Rational;
import expression.operator.Conditional;
import expression.operator.Equals;
import expression.operator.number.Add;
import expression.operator.number.Multiply;
import expression.operator.number.Subtract;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

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
    IExpression first =
        new Lambda(List.of("x", "y"), new Variable("x")).evaluate(this.environment);
    IExpression rest = new Lambda(List.of("x", "y"), new Variable("y"))
        .evaluate(this.environment);
    IExpression apply = new Lambda(List.of("f"),
        new Lambda(List.of("x", "y"),
            new FunctionCall(new Variable("f"),
                List.of(new Variable("x"), new Variable("y")))))
        .evaluate(this.environment);

    Variable one = new Variable("ONE");
    Variable two = new Variable("TWO");

    assertEquals(this.environment.getEntry("ONE"), first.evaluate(List.of(one, two),
        this.environment));
    assertEquals(this.environment.getEntry("TWO"),
        rest.evaluate(List.of(one, two), this.environment));
    assertEquals(this.environment.getEntry("ONE"), apply.evaluate(List.of(first), this.environment)
        .evaluate(List.of(one, two), this.environment));
    assertEquals(this.environment.getEntry("TWO"), apply.evaluate(List.of(rest), this.environment)
        .evaluate(List.of(one, two), this.environment));
  }

  @Test
  public void testFunctionErrors() throws Exception {
    Lambda testFunc = new Lambda(Arrays.asList("x"), new Variable("x"));

    try {
      testFunc.evaluate(Arrays.asList(new Rational(1)), this.environment);
      assert false; // Fail the test if an exception is not thrown.
    }
    catch (Exception e) {
      assert true;
    }

    List<IExpression> operands1 = Arrays.asList(new Variable("ONE"),
        new Variable("FOUR"));
    FunctionCall funcall = new FunctionCall(new Add(), operands1);

    try {
      funcall.evaluate(operands1, this.environment);
      assert false; // Fail the test if an exception is not thrown.
    }
    catch (Exception e) {
      assert true;
    }

    LambdaEnclosure func = new LambdaEnclosure(Arrays.asList("x"), new Variable("x"),
        this.environment);

    try {
      func.evaluate(operands1, this.environment);
      assert false; // Fail the test if an exception is not thrown.
    }
    catch (Exception e) {
      assert true;
    }
  }

  @Test
  public void testRecursion() throws Exception {

    Variable x = new Variable("x");
    Variable zero = new Variable("ZERO");
    Variable one = new Variable("ONE");
    Variable two = new Variable("TWO");

    IEnvironment recursiveEnv = new LocalContext(this.environment, new SymbolTable());

    IExpression sizeNum = new Lambda(List.of("x"),
        new FunctionCall(new Conditional(),
            List.of(new FunctionCall(new Equals(), List.of(x, zero)),
                new Rational(1, 1),
                new FunctionCall(new Multiply(),
                    List.of(two, new FunctionCall(new Variable("size-num"),
                        List.of(new FunctionCall(new Subtract(), List.of(x, one)))))))))
        .evaluate(recursiveEnv);
    recursiveEnv.addEntry("size-num", sizeNum);

    IExpression result = sizeNum.evaluate(List.of(new Rational(30, 1)),
        recursiveEnv);
    assertEquals(new Rational(1073741824), result);
  }
}
