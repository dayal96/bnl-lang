package Parser;

import org.junit.Test;

import java.io.StringReader;
import java.util.List;

import Exceptions.ArithmeticError;
import Expression.Expression;
import Expression.Composite;
import Expression.Number.Rational;
import Expression.Operator.Add;
import Expression.Operator.Divide;
import Expression.Operator.Multiply;
import Expression.Operator.Subtract;

import static org.junit.Assert.assertEquals;

/**
 * Class to test Parser.
 */
public class TestParser {

  @Test
  public void testParseSimple() throws ArithmeticError {
    String input = "(+ 1 2)";
    Parser parser = new Parser(new StringReader(input));
    List<Expression> expressions = parser.parseCode();

    assertEquals(1, expressions.size());
    assertEquals(new Composite(new Add(), new Rational(1, 1), new Rational(2, 1)),
            expressions.get(0));
  }

  @Test
  public void testParseComplex1() throws ArithmeticError {
    String input = "(+ (* 1 5) (* 2 (+ 7 9)))";
    Parser parser = new Parser(new StringReader(input));
    List<Expression> expressions = parser.parseCode();

    assertEquals(1, expressions.size());
    assertEquals(new Composite(new Add(),
                    new Composite(new Multiply(),
                            new Rational(1, 1),
                            new Rational(5, 1)),
                    new Composite(new Multiply(),
                            new Rational(2, 1),
                            new Composite(new Add(),
                                    new Rational(7, 1),
                                    new Rational(9, 1)))),
            expressions.get(0));
  }

  @Test
  public void testParseComplex2() throws ArithmeticError {
    String input = "(+ (* 1 5) (* 2 (/ 7 9))) (- 1 2)";
    Parser parser = new Parser(new StringReader(input));
    List<Expression> expressions = parser.parseCode();

    assertEquals(2, expressions.size());
    assertEquals(new Composite(new Add(),
                    new Composite(new Multiply(),
                            new Rational(1, 1),
                            new Rational(5, 1)),
                    new Composite(new Multiply(),
                            new Rational(2, 1),
                            new Composite(new Divide(),
                                    new Rational(7, 1),
                                    new Rational(9, 1)))),
            expressions.get(0));
    assertEquals(new Composite(new Subtract(), new Rational(1, 1), new Rational(2, 1)),
            expressions.get(1));
  }

  @Test
  public void testParseNoInput() throws ArithmeticError {
    String input = "";
    Parser parser = new Parser(new StringReader(input));
    List<Expression> expressions = parser.parseCode();
    assertEquals(0, expressions.size());
  }
}
