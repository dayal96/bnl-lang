package io.github.dayal96.expression.operator;

import static org.junit.Assert.assertEquals;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.cons.ConsPair;
import io.github.dayal96.expression.lambda.FunctionCall;
import io.github.dayal96.expression.struct.StructObject;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.type.StructType;
import io.github.dayal96.primitive.Empty;
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

    assertEquals("(cons 1 empty)", new FunctionCall(list, List.of(exp1))
        .evaluate(environment).toString());
    assertEquals("(cons 1 (cons \"hello\" empty))",
        new FunctionCall(list, List.of(exp1, exp2)).evaluate(environment).toString());
    assertEquals("(cons 1 (cons \"hello\" (cons true empty)))",
        new FunctionCall(list, List.of(exp1, exp2, exp3)).evaluate(environment).toString());
  }

  @Test
  public void testIsList() throws Exception {
    StructType posnType = new StructType("posn", List.of(NilType.NIL, NilType.NIL),
        List.of("x", "y"));
    AOperator isList = new IsList();
    List<Expression> notLists = List.of(isList,
        MyBoolean.FALSE,
        new Rational(1),
        new MyString("hello"),
        new StructObject(posnType, List.of(new Rational(1), new Rational(2))),
        new ConsPair(new Rational(1), new Rational(2)),
        new ConsPair(Empty.EMPTY, new Rational(1)));

    for (var expr : notLists) {
      assertEquals("Not a list, but passed list? : " + expr.toString(), MyBoolean.FALSE,
          new FunctionCall(isList, List.of(expr)).evaluate(environment));
    }

    List<Expression> lists = List.of(Empty.EMPTY,
        new ConsPair(new Rational(1), Empty.EMPTY),
        new ConsPair(new Rational(1), new ConsPair(new Rational(2), Empty.EMPTY)));
    for (var expr : lists) {
      assertEquals("Is a list, but failed list? : " + expr.toString(), MyBoolean.TRUE,
          new FunctionCall(isList, List.of(expr)).evaluate(environment));
    }
  }
}
