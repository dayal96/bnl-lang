package expression.operator;

import static org.junit.Assert.assertEquals;

import environment.IEnvironment;
import environment.SymbolTable;
import exceptions.ArithmeticError;
import exceptions.DivideByZeroError;
import expression.EmptyExpression;
import expression.IExpression;
import expression.Variable;
import expression.bool.MyBoolean;
import expression.lambda.FunctionCall;
import expression.number.ImproperFraction;
import expression.number.Rational;
import expression.operator.bool.And;
import expression.operator.bool.Or;
import expression.operator.number.Add;
import expression.operator.number.Divide;
import expression.operator.number.GreaterThan;
import expression.operator.number.LessThan;
import expression.operator.number.Multiply;
import expression.operator.number.Subtract;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

/**
 * Class to test Operators.
 */
public class TestOperators {

  private final IEnvironment environment;

  public TestOperators() throws Exception {
    this.environment = new SymbolTable();
    this.environment.addEntry("ONE", new Rational(1, 1));
    this.environment.addEntry("TWO", new Rational(2, 1));
    this.environment.addEntry("THREE", new Rational(3, 1));
    this.environment.addEntry("ONE_COPY", new Rational(1, 1));
  }

  @Test
  public void testAnd() throws Exception {
    AOperator and = new And();

    IExpression exp1 = new FunctionCall(and, Arrays.asList(MyBoolean.TRUE, MyBoolean.TRUE));
    IExpression exp2 = new FunctionCall(and, Arrays.asList(MyBoolean.TRUE, MyBoolean.FALSE));
    IExpression exp3 = new FunctionCall(and, Arrays.asList(MyBoolean.FALSE, MyBoolean.TRUE));
    IExpression exp4 = new FunctionCall(and, Arrays.asList(MyBoolean.FALSE, MyBoolean.FALSE));

    assertEquals(MyBoolean.TRUE, exp1.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.FALSE, exp2.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.FALSE, exp3.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.FALSE, exp4.evaluate(new SymbolTable()));

    List<IExpression> operands5 = Arrays.asList(MyBoolean.TRUE, MyBoolean.TRUE, MyBoolean.TRUE);
    IExpression exp5 = new FunctionCall(and, operands5);
    assertEquals(MyBoolean.TRUE, exp5.evaluate(new SymbolTable()));

    List<IExpression> operands6 = Arrays.asList(MyBoolean.TRUE, MyBoolean.FALSE, MyBoolean.TRUE);
    IExpression exp6 = new FunctionCall(and, operands6);
    assertEquals(MyBoolean.FALSE, exp6.evaluate(new SymbolTable()));

    List<IExpression> operands7 = Arrays.asList(MyBoolean.FALSE);
    IExpression exp7 = new FunctionCall(and, operands7);
    assertEquals(MyBoolean.FALSE, exp7.evaluate(new SymbolTable()));

    List<IExpression> operands8 = Arrays.asList(new Rational(15));
    IExpression exp8 = new FunctionCall(and, operands8);

    try {
      exp8.evaluate(new SymbolTable());
      assert false;
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands9 = Arrays.asList();
    IExpression exp9 = new FunctionCall(and, operands9);

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

    IExpression exp1 = new FunctionCall(or, Arrays.asList(MyBoolean.TRUE, MyBoolean.TRUE));
    IExpression exp2 = new FunctionCall(or, Arrays.asList(MyBoolean.TRUE, MyBoolean.FALSE));
    IExpression exp3 = new FunctionCall(or, Arrays.asList(MyBoolean.FALSE, MyBoolean.TRUE));
    IExpression exp4 = new FunctionCall(or, Arrays.asList(MyBoolean.FALSE, MyBoolean.FALSE));

    assertEquals(MyBoolean.TRUE, exp1.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.TRUE, exp2.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.TRUE, exp3.evaluate(new SymbolTable()));
    assertEquals(MyBoolean.FALSE, exp4.evaluate(new SymbolTable()));

    List<IExpression> operands5 = Arrays.asList(MyBoolean.FALSE, MyBoolean.FALSE, MyBoolean.FALSE);
    IExpression exp5 = new FunctionCall(or, operands5);
    assertEquals(MyBoolean.FALSE, exp5.evaluate(new SymbolTable()));

    List<IExpression> operands6 = Arrays.asList(MyBoolean.FALSE, MyBoolean.TRUE, MyBoolean.FALSE);
    IExpression exp6 = new FunctionCall(or, operands6);
    assertEquals(MyBoolean.TRUE, exp6.evaluate(new SymbolTable()));

    List<IExpression> operands7 = Arrays.asList(MyBoolean.FALSE);
    IExpression exp7 = new FunctionCall(or, operands7);
    assertEquals(MyBoolean.FALSE, exp7.evaluate(new SymbolTable()));

    List<IExpression> operands8 = Arrays.asList(new Rational(15));
    IExpression exp8 = new FunctionCall(or, operands8);

    try {
      exp8.evaluate(new SymbolTable());
      assert false;
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands9 = Arrays.asList();
    IExpression exp9 = new FunctionCall(or, operands9);

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
    IExpression v1 = new Rational(4, 5);
    IExpression v2 = new Rational(4, 5);
    AOperator add = new Add();

    List<IExpression> operands1 = new ArrayList<>();
    operands1.add(v1);
    operands1.add(v2);

    FunctionCall exp = new FunctionCall(add, operands1);
    assertEquals(new Rational(8, 5), exp.evaluate(new SymbolTable()));

    List<IExpression> operands2 = new ArrayList<>();
    operands2.add(exp);
    operands2.add(v2);

    FunctionCall exp2 = new FunctionCall(add, operands2);
    assertEquals(new Rational(12, 5), exp2.evaluate(new SymbolTable()));

    List<IExpression> operands3 = Arrays.asList(v1);
    FunctionCall exp3 = new FunctionCall(add, operands3);
    assertEquals(v1, exp3.evaluate(new SymbolTable()));

    List<IExpression> operands4 = new LinkedList<>();
    FunctionCall exp4 = new FunctionCall(add, operands4);

    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands5 = Arrays.asList(MyBoolean.TRUE);
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
    IExpression v1 = new Rational(4, 5);
    IExpression v2 = new Rational(4, 5);
    AOperator multiply = new Multiply();

    List<IExpression> operands1 = new ArrayList<>();
    operands1.add(v1);
    operands1.add(v2);

    FunctionCall exp = new FunctionCall(multiply, operands1);
    assertEquals(new Rational(16, 25), exp.evaluate(new SymbolTable()));

    List<IExpression> operands2 = new ArrayList<>();
    operands2.add(exp);
    operands2.add(v2);

    FunctionCall exp2 = new FunctionCall(multiply, operands2);
    assertEquals(new Rational(64, 125), exp2.evaluate(new SymbolTable()));

    List<IExpression> operands3 = Arrays.asList(v1);
    FunctionCall exp3 = new FunctionCall(multiply, operands3);
    assertEquals(v1, exp3.evaluate(new SymbolTable()));


    List<IExpression> operands4 = new LinkedList<>();
    FunctionCall exp4 = new FunctionCall(multiply, operands4);

    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands5 = Arrays.asList(MyBoolean.TRUE);
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
    IExpression v1 = new Rational(4, 5);
    IExpression v2 = new Rational(4, 5);
    AOperator subtract = new Subtract();

    List<IExpression> operands1 = new ArrayList<>();
    operands1.add(v1);
    operands1.add(v2);

    FunctionCall exp = new FunctionCall(subtract, operands1);
    assertEquals(new Rational(0), exp.evaluate(new SymbolTable()));

    List<IExpression> operands2 = new ArrayList<>();
    operands2.add(exp);
    operands2.add(v2);

    FunctionCall exp2 = new FunctionCall(subtract, operands2);
    assertEquals(new Rational(-4, 5), exp2.evaluate(new SymbolTable()));

    List<IExpression> operands3 = Arrays.asList(v1);
    FunctionCall exp3 = new FunctionCall(subtract, operands3);
    assertEquals(v1, exp3.evaluate(new SymbolTable()));

    List<IExpression> operands4 = new LinkedList<>();
    FunctionCall exp4 = new FunctionCall(subtract, operands4);

    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands5 = Arrays.asList(MyBoolean.TRUE);
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
    IExpression v1 = new Rational(4, 5);
    IExpression v2 = new Rational(4, 5);
    AOperator divide = new Divide();

    List<IExpression> operands1 = new ArrayList<>();
    operands1.add(v1);
    operands1.add(v2);

    FunctionCall exp = new FunctionCall(divide, operands1);
    assertEquals(new Rational(1), exp.evaluate(new SymbolTable()));

    List<IExpression> operands2 = new ArrayList<>();
    operands2.add(exp);
    operands2.add(v2);

    FunctionCall exp2 = new FunctionCall(divide, operands2);
    assertEquals(new Rational(5, 4), exp2.evaluate(new SymbolTable()));

    List<IExpression> operands3 = Arrays.asList(v1);
    FunctionCall exp3 = new FunctionCall(divide, operands3);
    assertEquals(v1, exp3.evaluate(new SymbolTable()));

    List<IExpression> operands4 = new LinkedList<>();
    FunctionCall exp4 = new FunctionCall(divide, operands4);

    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands5 = Arrays.asList(MyBoolean.TRUE);
    FunctionCall exp5 = new FunctionCall(divide, operands5);

    try {
      exp5.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands6 = Arrays.asList(new Rational(1), new Rational(0));
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
    IExpression v1 = new Rational(4, 5);
    IExpression v2 = new Rational(4, 5);
    IExpression v3 = new Rational(5, 4);
    AOperator gt = new GreaterThan();

    List<IExpression> operands1 = Arrays.asList(v1, v2);
    IExpression exp1 = new FunctionCall(gt, operands1);
    assertEquals(MyBoolean.FALSE, exp1.evaluate(new SymbolTable()));

    List<IExpression> operands2 = Arrays.asList(v1, v3);
    IExpression exp2 = new FunctionCall(gt, operands2);
    assertEquals(MyBoolean.FALSE, exp2.evaluate(new SymbolTable()));

    List<IExpression> operands3 = Arrays.asList(v3, v1);
    IExpression exp3 = new FunctionCall(gt, operands3);
    assertEquals(MyBoolean.TRUE, exp3.evaluate(new SymbolTable()));

    List<IExpression> operands4 = Arrays.asList(v1);
    IExpression exp4 = new FunctionCall(gt, operands4);
    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands5 = new ArrayList<>();
    IExpression exp5 = new FunctionCall(gt, operands5);

    try {
      exp5.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands6 = Arrays.asList(MyBoolean.TRUE);
    IExpression exp6 = new FunctionCall(gt, operands6);

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
    IExpression v1 = new Rational(4, 5);
    IExpression v2 = new Rational(4, 5);
    IExpression v3 = new Rational(5, 4);
    AOperator lt = new LessThan();

    List<IExpression> operands1 = Arrays.asList(v1, v2);
    IExpression exp1 = new FunctionCall(lt, operands1);
    assertEquals(MyBoolean.FALSE, exp1.evaluate(new SymbolTable()));

    List<IExpression> operands2 = Arrays.asList(v1, v3);
    IExpression exp2 = new FunctionCall(lt, operands2);
    assertEquals(MyBoolean.TRUE, exp2.evaluate(new SymbolTable()));

    List<IExpression> operands3 = Arrays.asList(v3, v1);
    IExpression exp3 = new FunctionCall(lt, operands3);
    assertEquals(MyBoolean.FALSE, exp3.evaluate(new SymbolTable()));

    List<IExpression> operands4 = Arrays.asList(v1);
    IExpression exp4 = new FunctionCall(lt, operands4);
    try {
      exp4.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands5 = new ArrayList<>();
    IExpression exp5 = new FunctionCall(lt, operands5);

    try {
      exp5.evaluate(new SymbolTable());
      assert false; // Fail test if no exception is thrown.
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    List<IExpression> operands6 = Arrays.asList(MyBoolean.TRUE);
    IExpression exp6 = new FunctionCall(lt, operands6);

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

    try {
      equals.evaluate(List.of(new Variable("ONE")), this.environment);
      assert false;
    }
    catch (IllegalArgumentException e) {
      assert true;
    }

    assertEquals("=", equals.toString());
  }
}
