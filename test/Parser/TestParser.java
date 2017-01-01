package Parser;

import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
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

    List<Expression> operands = new ArrayList<Expression>();
    operands.add(new Rational(1, 1));
    operands.add(new Rational(2, 1));

    assertEquals(1, expressions.size());
    assertEquals(new Composite(new Add(), operands),
            expressions.get(0));

    input = "(+ 1 2 )";
    parser = new Parser(new StringReader(input));
    expressions = parser.parseCode();

    operands = new ArrayList<Expression>();
    operands.add(new Rational(1, 1));
    operands.add(new Rational(2, 1));

    assertEquals(1, expressions.size());
    assertEquals(new Composite(new Add(), operands),
            expressions.get(0));
  }

  @Test
  public void testParseComplex1() throws ArithmeticError {
    String input = "(+ (* 1 5) (* 2 (+ 7 9)))";
    //                  EXP1    EXP2 EXP3
    // EXP3 is inside EXP2
    Parser parser = new Parser(new StringReader(input));
    List<Expression> expressions = parser.parseCode();

    List<Expression> operands1 = new ArrayList<Expression>();
    List<Expression> operands2 = new ArrayList<Expression>();
    List<Expression> operands3 = new ArrayList<Expression>();
    List<Expression> operandsToParse = new ArrayList<Expression>();

    operands3.add(new Rational(7, 1));
    operands3.add(new Rational(9, 1));
    Expression exp3 = new Composite(new Add(), operands3);

    operands2.add(new Rational(2, 1));
    operands2.add(exp3);
    Expression exp2 = new Composite(new Multiply(), operands2);

    operands1.add(new Rational(1, 1));
    operands1.add(new Rational(5, 1));
    Expression exp1 = new Composite(new Multiply(), operands1);

    operandsToParse.add(exp1);
    operandsToParse.add(exp2);
    Expression toParse = new Composite(new Add(), operandsToParse);

    assertEquals(1, expressions.size());
    assertEquals(toParse, expressions.get(0));
  }

  @Test
  public void testParseComplex2() throws ArithmeticError {
    String input = "(+ (* 1 5) (* 2 (/ 7 9))) (- 1 2)";
    Parser parser = new Parser(new StringReader(input));
    List<Expression> expressions = parser.parseCode();

    List<Expression> operands1 = new ArrayList<Expression>();
    List<Expression> operands2 = new ArrayList<Expression>();
    List<Expression> operands3 = new ArrayList<Expression>();
    List<Expression> operandsToParse1 = new ArrayList<Expression>();
    List<Expression> operandsToParse2 = new ArrayList<Expression>();

    operands3.add(new Rational(7, 1));
    operands3.add(new Rational(9, 1));
    Expression exp3 = new Composite(new Divide(), operands3);

    operands2.add(new Rational(2, 1));
    operands2.add(exp3);
    Expression exp2 = new Composite(new Multiply(), operands2);

    operands1.add(new Rational(1, 1));
    operands1.add(new Rational(5, 1));
    Expression exp1 = new Composite(new Multiply(), operands1);

    operandsToParse1.add(exp1);
    operandsToParse1.add(exp2);
    Expression toParse1 = new Composite(new Add(), operandsToParse1);

    operandsToParse2.add(new Rational(1, 1));
    operandsToParse2.add(new Rational(2, 1));
    Expression toParse2 = new Composite(new Subtract(), operandsToParse2);

    assertEquals(2, expressions.size());
    assertEquals(toParse1, expressions.get(0));
    assertEquals(toParse2, expressions.get(1));
  }

  @Test
  public void testParseNoInput() throws ArithmeticError {
    String input = "";
    Parser parser = new Parser(new StringReader(input));
    List<Expression> expressions = parser.parseCode();
    assertEquals(0, expressions.size());
  }
}
