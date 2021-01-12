package expression;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class TestComplex {
  private final ProgramTester tester;
  private final Map<String, String> tests;

  public TestComplex() {
    this.tester = new ProgramTester();
    this.tests = new LinkedHashMap<String, String>();
  }

  @Before
  public void addTests() {
    testPalindrome();
  }

  @Test
  public void testAll() throws Exception {
    this.tester.testPrograms(this.tests);
  }

  private void testPalindrome() {
    String prog =
        "(define empty #f)\n"
        + "\n"
        + "(define map \n"
        + "    (lambda (list func)\n"
        + "        (if (= empty list)\n"
        + "            empty\n"
        + "            (cons (func (first list))\n"
        + "                  (map (rest list) func)))))\n"
        + "\n"
        + "(map (cons 1 (cons 2 (cons -1 empty))) (lambda (x) (* x x)))\n";
    String expected = "(cons 1 (cons 4 (cons 1 false)))\n";
    this.tests.put(prog, expected);
  }
}
