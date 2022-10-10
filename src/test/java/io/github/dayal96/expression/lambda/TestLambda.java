package io.github.dayal96.expression.lambda;

import static org.junit.Assert.assertEquals;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.LocalContext;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.Variable;
import io.github.dayal96.primitive.number.Rational;
import io.github.dayal96.expression.operator.Conditional;
import io.github.dayal96.expression.operator.Equals;
import io.github.dayal96.expression.operator.number.Add;
import io.github.dayal96.expression.operator.number.Multiply;
import io.github.dayal96.expression.operator.number.Subtract;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class TestLambda {

  private final Environment environment;

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
    Expression first =
        new Lambda(List.of("x", "y"), new Variable("x")).evaluate(this.environment);
    Expression rest = new Lambda(List.of("x", "y"), new Variable("y"))
        .evaluate(this.environment);
    Expression apply = new Lambda(List.of("f"),
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
    Lambda testFunc = new Lambda(List.of("x"), new Variable("x"));

    try {
      testFunc.evaluate(List.of(new Rational(1)), this.environment);
      assert false; // Fail the test if an exception is not thrown.
    }
    catch (Exception e) {
      assert true;
    }

    List<Expression> operands1 = Arrays.asList(new Variable("ONE"),
        new Variable("FOUR"));
    FunctionCall funcall = new FunctionCall(new Add(), operands1);

    try {
      funcall.evaluate(operands1, this.environment);
      assert false; // Fail the test if an exception is not thrown.
    }
    catch (Exception e) {
      assert true;
    }

    LambdaEnclosure func = new LambdaEnclosure(List.of("x"), new Variable("x"),
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

    Environment recursiveEnv = new LocalContext(this.environment, new SymbolTable());

    Expression sizeNum = new Lambda(List.of("x"),
        new FunctionCall(new Conditional(),
            List.of(new FunctionCall(new Equals(), List.of(x, zero)),
                new Rational(1, 1),
                new FunctionCall(new Multiply(),
                    List.of(two, new FunctionCall(new Variable("size-num"),
                        List.of(new FunctionCall(new Subtract(), List.of(x, one)))))))))
        .evaluate(recursiveEnv);
    recursiveEnv.addEntry("size-num", sizeNum);

    Expression result = sizeNum.evaluate(List.of(new Rational(30, 1)),
        recursiveEnv);
    assertEquals(new Rational(1073741824), result);
  }
}
