package interpreter;

import java.io.Reader;
import java.util.List;
import java.util.function.Function;

class CupParserFunc implements Function<Reader, List<IEvaluable>> {

  @Override
  public List<IEvaluable> apply(Reader sourceCode) {
    Lexer lexer = new Lexer(sourceCode);
    CupParser parser = new CupParser(lexer);
    try {
      List<IEvaluable> program = ((List<IEvaluable>) parser.parse().value);
      return program;
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to parse test program.");
    }
  }
}
