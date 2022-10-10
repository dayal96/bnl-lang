package io.github.dayal96.expression.operator;

import static org.junit.Assert.assertEquals;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.primitive.bool.MyBoolean;
import io.github.dayal96.expression.lambda.FunctionCall;
import io.github.dayal96.primitive.number.Rational;
import io.github.dayal96.expression.operator.cons.Cons;
import io.github.dayal96.expression.operator.cons.First;
import io.github.dayal96.expression.operator.cons.Rest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class TestCons {
  private final Environment environment;

  public TestCons() {
    this.environment = new SymbolTable();
  }

  @Test
  public void testSimplePairs() throws Exception {
    AOperator cons = new Cons();
    AOperator first = new First();
    AOperator rest = new Rest();

    Expression exp1 = new Rational(-5);
    Expression exp2 = MyBoolean.TRUE;
    Expression exp3 = new FunctionCall(cons, Arrays.asList(exp1, exp2));

    assertEquals("(cons -5 true)", exp3.evaluate(this.environment).toString());

    Expression exp4 = new FunctionCall(first, List.of(exp3));
    Expression exp5 = new FunctionCall(rest, List.of(exp3));

    assertEquals(exp1, exp4.evaluate(new SymbolTable()));
    assertEquals(exp2, exp5.evaluate(new SymbolTable()));

    try {
      Expression tmp = new FunctionCall(cons, List.of(exp4));
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    try {
      Expression tmp = new FunctionCall(cons, Arrays.asList(exp1, exp2, exp4));
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    try {
      Expression tmp = new FunctionCall(first, List.of(exp1));
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    try {
      Expression tmp = new FunctionCall(first, new LinkedList<>());
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    try {
      Expression tmp = new FunctionCall(rest, List.of(exp1));
      tmp.evaluate(new SymbolTable());
      assert false; // Fail test if no exception was thrown.
    }
    catch (Exception e) {
      assert true;
    }

    try {
      Expression tmp = new FunctionCall(rest, new LinkedList<>());
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
