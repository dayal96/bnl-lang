package io.github.dayal96.expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.interpreter.Interpreter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.Optional;

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
    this.interpreter = new Interpreter(new TestEvaluator(SymbolTable.getBnlEnv(),
        this.output));
  }

  /**
   * Run the given programs and compare against expected output.
   * @param tests      The Map of test programs as keys and expected output as values.
   */
  public void testPrograms(Map<String, Optional<String>> tests) {
    for (Map.Entry<String, Optional<String>> testCase : tests.entrySet()) {
      try {
        this.interpreter.interpret(new StringReader(testCase.getKey()));
        String out = this.output.toString();
        if (testCase.getValue().isPresent()) {
          assertEquals(testCase.getValue().get().trim(), out.trim());
        }
        else {
          assert false;
        }
        StringBuffer buffer = this.output.getBuffer();
        buffer.delete(0, buffer.length()); // Because flush doesn't clear the buffer!!!
      }
      catch (AssertionError | Exception e) {
        if (testCase.getValue().isPresent()) {
          throw new AssertionError(e.getMessage());
        }
      }
    }
  }

}
