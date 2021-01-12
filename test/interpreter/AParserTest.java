package interpreter;

import static org.junit.Assert.assertEquals;

import expression.IExpression;
import expression.Variable;
import expression.bool.MyBoolean;
import expression.lambda.FunctionCall;
import expression.lambda.Lambda;
import expression.local.Local;
import expression.local.LocalDefinition;
import expression.number.Rational;
import expression.operator.Conditional;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.junit.Test;

public abstract class AParserTest {

  private final Function<Reader, List<IEvaluable>> parser;

  /**
   * Create an AParser to use for testing the given parser.
   *
   * @param parser The parser to test.
   */
  protected AParserTest(Function<Reader, List<IEvaluable>> parser) {
    this.parser = parser;
  }

  @Test
  public void testParsePrimitive() throws Exception {
    String prims = "2" + "\n" + "15/9" + "\n" + "-3/9" + "\n" + "#t" + "\n" + "#f" + "\n"
        + "variable-name var2";

    List<IEvaluable> evals = this.parser.apply(new StringReader(prims));

    List<IEvaluable> expectedEvals = List.of(new EvaluableExpression(new Rational(2, 1)),
        new EvaluableExpression(new Rational(15, 9)),
        new EvaluableExpression(new Rational(-3, 9)),
        new EvaluableExpression(MyBoolean.TRUE),
        new EvaluableExpression(MyBoolean.FALSE),
        new EvaluableExpression(new Variable("variable-name")),
        new EvaluableExpression(new Variable("var2")));

    for (int i = 0; i < evals.size(); i++) {
      assertEquals(expectedEvals.get(i), evals.get(i));
    }
  }

  @Test
  public void testParseCompound() throws Exception {
    String functions = "(lambda (x y) x)" + "\n"
        + "(define size-num (lambda (x) (if (= x 0) 1 (* 2 (size-num (- x 1))))))\n"
        + "size-num";

    List<IEvaluable> evals = this.parser.apply(new StringReader(functions));

    IExpression func1 = new Lambda(List.of("x", "y"), new Variable("x"));

    Variable x = new Variable("x");
    Rational zero = new Rational(0, 1);
    Rational one = new Rational(1, 1);
    Rational two = new Rational(2, 1);
    IExpression func2 = new Lambda(List.of("x"),
        new FunctionCall(new Conditional(),
            List.of(new FunctionCall(new Variable("="), List.of(x, zero)),
                new Rational(1, 1),
                new FunctionCall(new Variable("*"),
                    List.of(two, new FunctionCall(new Variable("size-num"),
                        List.of(new FunctionCall(new Variable("-"), List.of(x, one)))))))));

    LocalDefinition sizeNumDef = new LocalDefinition("size-num", func2);
    Local expectedSizeNum = new Local(Arrays.asList(sizeNumDef), new Variable("size-num"));

    List<IEvaluable> expectedEvals = List.of(new EvaluableExpression(func1),
        new EvaluableExpression(expectedSizeNum));

    for (int i = 0; i < evals.size(); i++) {
      // Compare string representations because functions cannot be tested for equality.
      assertEquals(expectedEvals.get(i).toString(), evals.get(i).toString());
    }
  }
}
