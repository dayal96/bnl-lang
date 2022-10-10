package io.github.dayal96.expression.operator;

import static org.junit.Assert.assertEquals;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.exceptions.DivideByZeroError;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.Variable;
import io.github.dayal96.expression.operator.number.IsNumber;
import io.github.dayal96.expression.operator.string.IsString;
import io.github.dayal96.expression.operator.string.NumToString;
import io.github.dayal96.expression.operator.string.StringAppend;
import io.github.dayal96.expression.operator.string.StringContains;
import io.github.dayal96.expression.operator.string.StringLength;
import io.github.dayal96.expression.operator.string.Substring;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.primitive.bool.MyBoolean;
import io.github.dayal96.expression.lambda.FunctionCall;
import io.github.dayal96.primitive.number.Rational;
import io.github.dayal96.expression.operator.bool.And;
import io.github.dayal96.expression.operator.bool.Or;
import io.github.dayal96.expression.operator.number.Add;
import io.github.dayal96.expression.operator.number.Divide;
import io.github.dayal96.expression.operator.number.GreaterThan;
import io.github.dayal96.expression.operator.number.LessThan;
import io.github.dayal96.expression.operator.number.Multiply;
import io.github.dayal96.expression.operator.number.Subtract;
import io.github.dayal96.primitive.string.MyString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

/**
 * Class to test Operators.
 */
public class TestOperators {

  private final Environment environment;

  public TestOperators() throws Exception {
    this.environment = new SymbolTable();
    this.environment.addEntry("ONE", new Rational(1, 1));
    this.environment.addEntry("TWO", new Rational(2, 1));
    this.environment.addEntry("THREE", new Rational(3, 1));
    this.environment.addEntry("ONE_COPY", new Rational(1, 1));
    this.environment.addEntry("ABRA", new MyString("ABRA"));
    this.environment.addEntry("CADABRA", new MyString("CADABRA"));
    this.environment.addEntry("ABRA_COPY", new MyString("ABRA"));
  }

  @Test
  public void testIsNumber() throws Exception {
    AOperator isNumber = new IsNumber();

    for (var number : List.of("ONE", "TWO", "THREE", "ONE_COPY")) {
      assertEquals(MyBoolean.TRUE, isNumber.evaluate(List.of(new Variable(number)),
          this.environment));
    }

    for (var str : List.of("ABRA", "CADABRA", "ABRA_COPY")) {
      assertEquals(MyBoolean.FALSE, isNumber.evaluate(List.of(new Variable(str)),
          this.environment));
    }

    assertEquals("number?", isNumber.toString());
    assertEquals(NilType.NIL, isNumber.getType());
  }

  @Test
  public void testAnd() throws Exception {
    AOperator and = new And();

    Expression exp1 = new FunctionCall(and, Arrays.asList(MyBoolean.TRUE, MyBoolean.TRUE));
    Expression exp2 = new FunctionCall(and, Arrays.asList(MyBoolean.TRUE, MyBoolean.FALSE));
    Expression exp3 = new FunctionCall(and, Arrays.asList(MyBoolean.FALSE, MyBoolean.TRUE));
    Expression exp4 = new FunctionCall(and, Arrays.asList(MyBoolean.FALSE, MyBoolean.FALSE));

    assertEquals(MyBoolean.TRUE, exp1.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.FALSE, exp2.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.FALSE, exp3.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.FALSE, exp4.evaluate(new SymbolTable()));

    List<Expression> operands5 = Arrays.asList(MyBoolean.TRUE, MyBoolean.TRUE, MyBoolean.TRUE);
    Expression exp5 = new FunctionCall(and, operands5);
    assertEquals(MyBoolean.TRUE, exp5.evaluate(new SymbolTable()));

    List<Expression> operands6 = Arrays.asList(MyBoolean.TRUE, MyBoolean.FALSE, MyBoolean.TRUE);
    Expression exp6 = new FunctionCall(and, operands6);
    assertEquals(MyBoolean.FALSE, exp6.evaluate(new SymbolTable()));

    List<Expression> operands7 = List.of(MyBoolean.FALSE);
    Expression exp7 = new FunctionCall(and, operands7);
    assertEquals(MyBoolean.FALSE, exp7.evaluate(new SymbolTable()));

    List<Expression> operands8 = List.of(new Rational(15));
    Expression exp8 = new FunctionCall(and, operands8);

    try {
      exp8.evaluate(new SymbolTable());
      assert false;
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands9 = List.of();
    Expression exp9 = new FunctionCall(and, operands9);

    try {
      exp9.evaluate(new SymbolTable());
      assert false;
    }
    catch (IllegalArgumentException e) {
      assert true;
    }
  }

  @Test
  public void testOr() throws Exception {
    AOperator or = new Or();

    Expression exp1 = new FunctionCall(or, Arrays.asList(MyBoolean.TRUE, MyBoolean.TRUE));
    Expression exp2 = new FunctionCall(or, Arrays.asList(MyBoolean.TRUE, MyBoolean.FALSE));
    Expression exp3 = new FunctionCall(or, Arrays.asList(MyBoolean.FALSE, MyBoolean.TRUE));
    Expression exp4 = new FunctionCall(or, Arrays.asList(MyBoolean.FALSE, MyBoolean.FALSE));

    assertEquals(MyBoolean.TRUE, exp1.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.TRUE, exp2.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.TRUE, exp3.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.FALSE, exp4.evaluate(new SymbolTable()));

    List<Expression> operands5 = Arrays.asList(MyBoolean.FALSE, MyBoolean.FALSE, MyBoolean.FALSE);
    Expression exp5 = new FunctionCall(or, operands5);
    assertEquals(MyBoolean.FALSE, exp5.evaluate(new SymbolTable()));

    List<Expression> operands6 = Arrays.asList(MyBoolean.FALSE, MyBoolean.TRUE, MyBoolean.FALSE);
    Expression exp6 = new FunctionCall(or, operands6);
    assertEquals(MyBoolean.TRUE, exp6.evaluate(new SymbolTable()));

    List<Expression> operands7 = List.of(MyBoolean.FALSE);
    Expression exp7 = new FunctionCall(or, operands7);
    assertEquals(MyBoolean.FALSE, exp7.evaluate(new SymbolTable()));

    List<Expression> operands8 = List.of(new Rational(15));
    Expression exp8 = new FunctionCall(or, operands8);

    try {
      exp8.evaluate(new SymbolTable());
      assert false;
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands9 = List.of();
    Expression exp9 = new FunctionCall(or, operands9);

    try {
      exp9.evaluate(new SymbolTable());
      assert false;
    }
    catch (IllegalArgumentException e) {
      assert true;
    }
  }

  @Test
  public void testAdd() throws Exception {
    Expression v1 = new Rational(4, 5);
    Expression v2 = new Rational(4, 5);
    AOperator add = new Add();

    List<Expression> operands1 = new ArrayList<>();
    operands1.add(v1);
    operands1.add(v2);

    FunctionCall exp = new FunctionCall(add, operands1);
    assertEquals(new Rational(8, 5), exp.evaluate(new SymbolTable()));

    List<Expression> operands2 = new ArrayList<>();
    operands2.add(exp);
    operands2.add(v2);

    FunctionCall exp2 = new FunctionCall(add, operands2);
    assertEquals(new Rational(12, 5), exp2.evaluate(new SymbolTable()));

    List<Expression> operands3 = List.of(v1);
    FunctionCall exp3 = new FunctionCall(add, operands3);
    assertEquals(v1, exp3.evaluate(new SymbolTable()));

    List<Expression> operands4 = new LinkedList<>();
    FunctionCall exp4 = new FunctionCall(add, operands4);

    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands5 = List.of(MyBoolean.TRUE);
    FunctionCall exp5 = new FunctionCall(add, operands5);

    try {
      exp5.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    assertEquals("+", add.toString());
  }

  @Test
  public void testMultiply() throws Exception {
    Expression v1 = new Rational(4, 5);
    Expression v2 = new Rational(4, 5);
    AOperator multiply = new Multiply();

    List<Expression> operands1 = new ArrayList<>();
    operands1.add(v1);
    operands1.add(v2);

    FunctionCall exp = new FunctionCall(multiply, operands1);
    assertEquals(new Rational(16, 25), exp.evaluate(new SymbolTable()));

    List<Expression> operands2 = new ArrayList<>();
    operands2.add(exp);
    operands2.add(v2);

    FunctionCall exp2 = new FunctionCall(multiply, operands2);
    assertEquals(new Rational(64, 125), exp2.evaluate(new SymbolTable()));

    List<Expression> operands3 = List.of(v1);
    FunctionCall exp3 = new FunctionCall(multiply, operands3);
    assertEquals(v1, exp3.evaluate(new SymbolTable()));


    List<Expression> operands4 = new LinkedList<>();
    FunctionCall exp4 = new FunctionCall(multiply, operands4);

    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands5 = List.of(MyBoolean.TRUE);
    FunctionCall exp5 = new FunctionCall(multiply, operands5);

    try {
      exp5.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    assertEquals("*", multiply.toString());
  }

  @Test
  public void testSubtract() throws Exception {
    Expression v1 = new Rational(4, 5);
    Expression v2 = new Rational(4, 5);
    AOperator subtract = new Subtract();

    List<Expression> operands1 = new ArrayList<>();
    operands1.add(v1);
    operands1.add(v2);

    FunctionCall exp = new FunctionCall(subtract, operands1);
    assertEquals(new Rational(0), exp.evaluate(new SymbolTable()));

    List<Expression> operands2 = new ArrayList<>();
    operands2.add(exp);
    operands2.add(v2);

    FunctionCall exp2 = new FunctionCall(subtract, operands2);
    assertEquals(new Rational(-4, 5), exp2.evaluate(new SymbolTable()));

    List<Expression> operands3 = List.of(v1);
    FunctionCall exp3 = new FunctionCall(subtract, operands3);
    assertEquals(v1, exp3.evaluate(new SymbolTable()));

    List<Expression> operands4 = new LinkedList<>();
    FunctionCall exp4 = new FunctionCall(subtract, operands4);

    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands5 = List.of(MyBoolean.TRUE);
    FunctionCall exp5 = new FunctionCall(subtract, operands5);

    try {
      exp5.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    assertEquals("-", subtract.toString());
  }

  @Test
  public void testDivide() throws Exception {
    Expression v1 = new Rational(4, 5);
    Expression v2 = new Rational(4, 5);
    AOperator divide = new Divide();

    List<Expression> operands1 = new ArrayList<>();
    operands1.add(v1);
    operands1.add(v2);

    FunctionCall exp = new FunctionCall(divide, operands1);
    assertEquals(new Rational(1), exp.evaluate(new SymbolTable()));

    List<Expression> operands2 = new ArrayList<>();
    operands2.add(exp);
    operands2.add(v2);

    FunctionCall exp2 = new FunctionCall(divide, operands2);
    assertEquals(new Rational(5, 4), exp2.evaluate(new SymbolTable()));

    List<Expression> operands3 = List.of(v1);
    FunctionCall exp3 = new FunctionCall(divide, operands3);
    assertEquals(v1, exp3.evaluate(new SymbolTable()));

    List<Expression> operands4 = new LinkedList<>();
    FunctionCall exp4 = new FunctionCall(divide, operands4);

    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands5 = List.of(MyBoolean.TRUE);
    FunctionCall exp5 = new FunctionCall(divide, operands5);

    try {
      exp5.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands6 = Arrays.asList(new Rational(1), new Rational(0));
    FunctionCall exp6 = new FunctionCall(divide, operands6);

    try {
      exp6.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (DivideByZeroError e) {
      assert true;
    }

    assertEquals("/", divide.toString());
  }

  @Test
  public void testGreaterThan() throws Exception {
    Expression v1 = new Rational(4, 5);
    Expression v2 = new Rational(4, 5);
    Expression v3 = new Rational(5, 4);
    AOperator gt = new GreaterThan();

    List<Expression> operands1 = Arrays.asList(v1, v2);
    Expression exp1 = new FunctionCall(gt, operands1);
    assertEquals(MyBoolean.FALSE, exp1.evaluate(new SymbolTable()));

    List<Expression> operands2 = Arrays.asList(v1, v3);
    Expression exp2 = new FunctionCall(gt, operands2);
    assertEquals(MyBoolean.FALSE, exp2.evaluate(new SymbolTable()));

    List<Expression> operands3 = Arrays.asList(v3, v1);
    Expression exp3 = new FunctionCall(gt, operands3);
    assertEquals(MyBoolean.TRUE, exp3.evaluate(new SymbolTable()));

    List<Expression> operands4 = List.of(v1);
    Expression exp4 = new FunctionCall(gt, operands4);
    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands5 = new ArrayList<>();
    Expression exp5 = new FunctionCall(gt, operands5);

    try {
      exp5.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands6 = List.of(MyBoolean.TRUE);
    Expression exp6 = new FunctionCall(gt, operands6);

    try {
      exp6.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    assertEquals(">", gt.toString());
  }

  @Test
  public void testLessThan() throws Exception {
    Expression v1 = new Rational(4, 5);
    Expression v2 = new Rational(4, 5);
    Expression v3 = new Rational(5, 4);
    AOperator lt = new LessThan();

    List<Expression> operands1 = Arrays.asList(v1, v2);
    Expression exp1 = new FunctionCall(lt, operands1);
    assertEquals(MyBoolean.FALSE, exp1.evaluate(new SymbolTable()));

    List<Expression> operands2 = Arrays.asList(v1, v3);
    Expression exp2 = new FunctionCall(lt, operands2);
    assertEquals(MyBoolean.TRUE, exp2.evaluate(new SymbolTable()));

    List<Expression> operands3 = Arrays.asList(v3, v1);
    Expression exp3 = new FunctionCall(lt, operands3);
    assertEquals(MyBoolean.FALSE, exp3.evaluate(new SymbolTable()));

    List<Expression> operands4 = List.of(v1);
    Expression exp4 = new FunctionCall(lt, operands4);
    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands5 = new ArrayList<>();
    Expression exp5 = new FunctionCall(lt, operands5);

    try {
      exp5.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<Expression> operands6 = List.of(MyBoolean.TRUE);
    Expression exp6 = new FunctionCall(lt, operands6);

    try {
      exp6.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    assertEquals("<", lt.toString());
  }

  @Test
  public void testEquals() throws Exception {
    AOperator equals = new Equals();
    assertEquals(MyBoolean.FALSE, equals.evaluate(List.of(new Variable("ONE"), new Variable("TWO"))
        , this.environment));
    assertEquals(MyBoolean.FALSE,
        equals.evaluate(List.of(new Variable("THREE"), new Variable("ONE"))
            , this.environment));
    assertEquals(MyBoolean.TRUE, equals.evaluate(List.of(new Variable("ONE"), new Variable("ONE"))
        , this.environment));
    assertEquals(MyBoolean.TRUE,
        equals.evaluate(List.of(new Variable("ONE"), new Variable("ONE_COPY"))
            , this.environment));
    assertEquals(MyBoolean.FALSE,
        equals.evaluate(List.of(new Variable("THREE"), new Variable("TWO"))
            , this.environment));

    assertEquals(MyBoolean.FALSE,
        equals.evaluate(List.of(new Variable("THREE"), new Variable("ABRA"))
            , this.environment));
    assertEquals(MyBoolean.FALSE,
        equals.evaluate(List.of(new Variable("CADABRA"), new Variable("TWO"))
            , this.environment));
    assertEquals(MyBoolean.TRUE,
        equals.evaluate(List.of(new Variable("ABRA"), new Variable("ABRA"))
            , this.environment));
    assertEquals(MyBoolean.TRUE,
        equals.evaluate(List.of(new Variable("CADABRA"), new Variable("CADABRA"))
            , this.environment));
    assertEquals(MyBoolean.TRUE,
        equals.evaluate(List.of(new Variable("ABRA"), new Variable("ABRA_COPY"))
            , this.environment));
    assertEquals(MyBoolean.TRUE,
        equals.evaluate(List.of(new Variable("ABRA_COPY"), new Variable("ABRA"))
            , this.environment));

    try {
      equals.evaluate(List.of(new Variable("ONE")), this.environment);
      assert false;
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    assertEquals("=", equals.toString());
  }

  @Test
  public void testIsString() throws Exception {
    AOperator isString = new IsString();

    for (var number : List.of("ONE", "TWO", "THREE", "ONE_COPY")) {
      assertEquals(MyBoolean.FALSE, isString.evaluate(List.of(new Variable(number)),
          this.environment));
    }

    for (var str : List.of("ABRA", "CADABRA", "ABRA_COPY")) {
      assertEquals(MyBoolean.TRUE, isString.evaluate(List.of(new Variable(str)),
          this.environment));
    }

    assertEquals("string?", isString.toString());
    assertEquals(NilType.NIL, isString.getType());
  }

  @Test
  public void testNumToString() throws Exception {
    AOperator numToString = new NumToString();

    assertEquals(new MyString("1"), numToString.evaluate(List.of(new Variable("ONE")),
        this.environment));
    assertEquals(new MyString("2"), numToString.evaluate(List.of(new Variable("TWO")),
        this.environment));
    assertEquals(new MyString("3"), numToString.evaluate(List.of(new Variable("THREE")),
        this.environment));

    assertEquals("num->string", numToString.toString());
    assertEquals(NilType.NIL, numToString.getType());
  }

  @Test
  public void testStringLength() throws Exception {
    AOperator stringLength = new StringLength();

    assertEquals(new Rational(0), stringLength.evaluate(List.of(new MyString("")),
        this.environment));
    assertEquals(new Rational(4), stringLength.evaluate(List.of(new Variable("ABRA")),
        this.environment));
    assertEquals(new Rational(7), stringLength.evaluate(List.of(new Variable("CADABRA")),
        this.environment));

    assertEquals("string-length", stringLength.toString());
    assertEquals(NilType.NIL, stringLength.getType());
  }

  @Test
  public void testSubstring() throws Exception {
    AOperator substring = new Substring();

    assertEquals(new MyString(""), substring.evaluate(List.of(
            new Rational(3), new Rational(0), new MyString("ABRA")), this.environment));
    assertEquals(new MyString("AB"), substring.evaluate(List.of(
        new Rational(0), new Rational(2), new MyString("ABRA")), this.environment));
    assertEquals(new MyString("ABRA"), substring.evaluate(List.of(
        new Rational(0), new Rational(4), new MyString("ABRA")), this.environment));
    assertEquals(new MyString("A"), substring.evaluate(List.of(
        new Rational(3), new Rational(1), new MyString("ABRA")), this.environment));

    assertEquals("substring", substring.toString());
    assertEquals(NilType.NIL, substring.getType());
  }

  @Test
  public void testStringAppend() throws Exception {
    AOperator stringAppend = new StringAppend();

    assertEquals(new MyString("CADABRA"), stringAppend.evaluate(List.of(new MyString("CAD"),
        new MyString("ABRA")), this.environment));
    assertEquals(new MyString("ABRA"), stringAppend.evaluate(List.of(new MyString("AB"),
        new MyString("RA")), this.environment));
    assertEquals(new MyString("ABRA CADABRA"), stringAppend.evaluate(List.of(new MyString("ABRA"),
            new MyString(" "), new MyString("CADABRA")), this.environment));

    assertEquals("string-append", stringAppend.toString());
    assertEquals(NilType.NIL, stringAppend.getType());
  }

  @Test
  public void testStringContains() throws Exception {
    AOperator stringContains = new StringContains();

    for (var c : "CADABRA".split("")) {
      assertEquals(MyBoolean.TRUE, stringContains.evaluate(List.of(new MyString("CADABRA"),
          new MyString(c)), this.environment));
    }

    assertEquals(MyBoolean.TRUE, stringContains.evaluate(List.of(new MyString("CADABRA"),
        new MyString("ABRA")), this.environment));
    assertEquals(MyBoolean.TRUE, stringContains.evaluate(List.of(new MyString("CADABRA"),
        new MyString("CAD")), this.environment));
    assertEquals(MyBoolean.FALSE, stringContains.evaluate(List.of(new MyString("CADABRA"),
        new MyString("ABRACA")), this.environment));
    assertEquals(MyBoolean.TRUE, stringContains.evaluate(List.of(new MyString("CADABRA"),
        new MyString("")), this.environment));

    assertEquals("string-contains?", stringContains.toString());
    assertEquals(NilType.NIL, stringContains.getType());
  }
}
