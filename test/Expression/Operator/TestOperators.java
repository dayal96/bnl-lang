package Expression.Operator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import Exceptions.ArithmeticError;
import Expression.Composite;
import Expression.Number.ImproperFraction;
import Expression.Number.Rational;
import Expression.Expression;

import static org.junit.Assert.assertEquals;

/**
 * Class to test Operators.
 */
public class TestOperators {

  @Test
  public void testAdd() throws ArithmeticError {
    Expression v1 = new Rational(new ImproperFraction(4, 5));
    Expression v2 = new Rational(new ImproperFraction(4, 5));
    Operator add = new Add();

    List<Expression> operands1 = new ArrayList<Expression>();
    operands1.add(v1);
    operands1.add(v2);

    Composite exp = new Composite(add, operands1);
    assertEquals(new Rational(new ImproperFraction(8, 5)), exp.evaluate());

    List<Expression> operands2 = new ArrayList<Expression>();
    operands2.add(exp);
    operands2.add(v2);

    Composite exp2 = new Composite(add, operands2);
    assertEquals(new Rational(new ImproperFraction(12, 5)), exp2.evaluate());
  }

  @Test
  public void testMultiply() throws ArithmeticError {
    Expression v1 = new Rational(new ImproperFraction(4, 5));
    Expression v2 = new Rational(new ImproperFraction(4, 5));
    Operator multiply = new Multiply();

    List<Expression> operands1 = new ArrayList<Expression>();
    operands1.add(v1);
    operands1.add(v2);

    Composite exp = new Composite(multiply, operands1);
    assertEquals(new Rational(new ImproperFraction(16, 25)), exp.evaluate());

    List<Expression> operands2 = new ArrayList<Expression>();
    operands2.add(exp);
    operands2.add(v2);

    Composite exp2 = new Composite(multiply, operands2);
    assertEquals(new Rational(new ImproperFraction(64, 125)), exp2.evaluate());
  }
}
