package io.github.dayal96.expression.operator;

import static org.junit.Assert.assertEquals;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.lambda.FunctionCall;
import io.github.dayal96.primitive.bool.MyBoolean;
import io.github.dayal96.primitive.number.Rational;
import io.github.dayal96.primitive.string.MyString;
import java.util.List;
import org.junit.Test;

public class TestList {
  private final Environment environment;

  public TestList() {
    this.environment = new SymbolTable();
  }

  @Test
  public void testList() throws Exception {
    AOperator list = new ListOperator();
    Expression exp1 = new Rational(1);
    Expression exp2 = new MyString("hello");
    Expression exp3 = MyBoolean.TRUE;

    assertEquals("(cons 1 false)", new FunctionCall(list, List.of(exp1))
        .evaluate(environment).toString());
    assertEquals("(cons 1 (cons \"hello\" false))",
        new FunctionCall(list, List.of(exp1, exp2)).evaluate(environment).toString());
    assertEquals("(cons 1 (cons \"hello\" (cons true false)))",
        new FunctionCall(list, List.of(exp1, exp2, exp3)).evaluate(environment).toString());
  }
}
