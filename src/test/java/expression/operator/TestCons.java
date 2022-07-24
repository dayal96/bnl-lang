package expression.operator;

import static org.junit.Assert.assertEquals;

import environment.IEnvironment;
import environment.SymbolTable;
import expression.IExpression;
import primitive.bool.MyBoolean;
import expression.lambda.FunctionCall;
import primitive.number.Rational;
import expression.operator.cons.Cons;
import expression.operator.cons.First;
import expression.operator.cons.Rest;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.Test;

public class TestCons {
  private final IEnvironment environment;

  public TestCons() {
    this.environment = new SymbolTable();
  }

  @Test
  public void testSimplePairs() throws Exception {
    AOperator cons = new Cons();
    AOperator first = new First();
    AOperator rest = new Rest();

    IExpression exp1 = new Rational(-5);
    IExpression exp2 = MyBoolean.TRUE;
    IExpression exp3 = new FunctionCall(cons, Arrays.asList(exp1, exp2));

    assertEquals("(cons -5 true)", exp3.evaluate(this.environment).toString());

    IExpression exp4 = new FunctionCall(first, Arrays.asList(exp3));
    IExpression exp5 = new FunctionCall(rest, Arrays.asList(exp3));

    assertEquals(exp1, exp4.evaluate(new SymbolTable()));
    assertEquals(exp2, exp5.evaluate(new SymbolTable()));

    try {
      IExpression tmp = new FunctionCall(cons, Arrays.asList(exp4));
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    try {
      IExpression tmp = new FunctionCall(cons, Arrays.asList(exp1, exp2, exp4));
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    try {
      IExpression tmp = new FunctionCall(first, Arrays.asList(exp1));
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    try {
      IExpression tmp = new FunctionCall(first, new LinkedList<>());
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    try {
      IExpression tmp = new FunctionCall(rest, Arrays.asList(exp1));
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    try {
      IExpression tmp = new FunctionCall(rest, new LinkedList<>());
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    assertEquals("cons", cons.toString());
    assertEquals("first", first.toString());
    assertEquals("rest", rest.toString());
  }
}
