package interpreter;

import interpreter.AParserTest;
import java.io.Reader;
import java.util.List;
import java.util.function.Function;

public class CupParserTest extends AParserTest {
  static class CupParserFunc implements Function<Reader, List<IEvaluable>> {
    @Override
    public List<IEvaluable> apply(Reader sourceCode) {
      Lexer lexer = new Lexer(sourceCode);
      CupParser parser = new CupParser(lexer);
      try {
        List<IEvaluable> program = ((List<IEvaluable>) parser.parse().value);
        return program;
      }
      catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to parse test program.");
      }
    }
  };

  /**
   * Create a CupParser to use for testing.
   */
  public CupParserTest() {
    super(new CupParserFunc());
  }
}
