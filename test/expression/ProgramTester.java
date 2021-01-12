package expression;

import static org.junit.Assert.assertEquals;

import environment.SymbolTable;
import interpreter.Interpreter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

/**
 * Convenience Class to make testing programs using a test interpreter easier.
 *
 * This class should only be used for end-to-end tests or integration tests, since it relies on a
 * functional syntactic parser to test semantics.
 */
public class ProgramTester {
  private final Interpreter interpreter;
  private final StringWriter output;

  public ProgramTester() {
    this.output = new StringWriter();
    this.interpreter = new Interpreter(new TestEvaluator(SymbolTable.primitiveOperations,
        this.output));
  }

  /**
   * Run the given programs and compare against expected output.
   * @param tests      The Map of test programs as keys and expected output as values.
   * @throws Exception
   */
  public void testPrograms(Map<String, String> tests) throws Exception {
    for (Map.Entry<String, String> testCase : tests.entrySet()) {
      this.interpreter.interpret(new StringReader(testCase.getKey()));
      String out = this.output.toString();
      assertEquals(testCase.getValue(), out);
      StringBuffer buffer = this.output.getBuffer();
      buffer.delete(0, buffer.length()); // Because flush doesn't clear the buffer!!!
    }
  }

}
