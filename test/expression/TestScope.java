package expression;

import static org.junit.Assert.assertEquals;

import environment.IEnvironment;
import environment.SymbolTable;
import interpreter.Interpreter;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Test;

public class TestScope {

  private final Interpreter interpreter;
  private final StringWriter output;

  public TestScope() {
    this.output = new StringWriter();
    this.interpreter = new Interpreter(new TestEvaluator(SymbolTable.primitiveOperations,
        this.output));
  }

  @Test
  public void testFunctionClosure() throws Exception {
    String scope1 = "(define add-x (lambda (x) (lambda (y) (+ y x))))\n"
        + "(define add-5 (add-x 5))\n"
        + "(define add-1 (add-x 1))\n"
        + "(define sub-3 (add-x -3))\n"
        + "(define x 12)\n"
        + "(cons (add-1 x) (cons (add-5 x) (sub-3 x)))\n";
    String expected = "(cons 13 (cons 17 9))\n";

    this.interpreter.interpret(new StringReader(scope1));
    String out = this.output.toString();
    this.output.flush();
    assertEquals(expected, out);
  }

  @Test
  public void testShadowing() throws Exception {
    String scope1 = "(define mirror-x (lambda (x) (lambda (x) x)))\n"
        + "(define mirror-5 (mirror-x 5))\n"
        + "(define mirror-1 (mirror-x 1))\n"
        + "(define mirror-3 (mirror-x -3))\n"
        + "(define x 12)\n"
        + "(cons (mirror-1 x) (cons (mirror-5 x) (mirror-3 x)))\n";
    String expected = "(cons 12 (cons 12 12))\n";

    this.interpreter.interpret(new StringReader(scope1));
    String out = this.output.toString();
    this.output.flush();
    assertEquals(expected, out);
  }
}
