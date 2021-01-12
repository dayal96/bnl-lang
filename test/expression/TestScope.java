package expression;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class TestScope {

  private final ProgramTester tester;
  private final Map<String, String> tests;

  public TestScope() {
    this.tester = new ProgramTester();
    this.tests = new LinkedHashMap<String, String>();
  }

  @Before
  public void addTests() {
    testFunctionClosure();
    testShadowing();
  }

  @Test
  public void testAll() throws Exception {
    this.tester.testPrograms(this.tests);
  }

  private void testFunctionClosure() {
    String prog = "(define add-x (lambda (x) (lambda (y) (+ y x))))\n"
        + "(define add-5 (add-x 5))\n"
        + "(define add-1 (add-x 1))\n"
        + "(define sub-3 (add-x -3))\n"
        + "(define x 12)\n"
        + "(cons (add-1 x) (cons (add-5 x) (sub-3 x)))\n";
    String expected = "(cons 13 (cons 17 9))\n";
    this.tests.put(prog, expected);
  }

  private void testShadowing() {
    String prog = "(define mirror-x (lambda (x) (lambda (x) x)))\n"
        + "(define mirror-5 (mirror-x 5))\n"
        + "(define mirror-1 (mirror-x 1))\n"
        + "(define mirror-3 (mirror-x -3))\n"
        + "(define x 12)\n"
        + "(cons (mirror-1 x) (cons (mirror-5 x) (mirror-3 x)))\n";
    String expected = "(cons 12 (cons 12 12))\n";
    this.tests.put(prog, expected);
  }
}
