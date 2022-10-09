package io.github.dayal96.interpreter;

import io.github.dayal96.absyn.Absyn;
import io.github.dayal96.absyn.transform.AbsynToExprList;
import io.github.dayal96.antlr.BnlLexer;
import io.github.dayal96.antlr.BnlParser;
import java.io.Reader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class AntlrInterpreterTest extends AParserTest {

  /**
   * Run the tests against the Antlr interpreter.
   */
  public AntlrInterpreterTest() {
    super(new AntlrParserFunc());
  }

  private static class AntlrParserFunc implements Function<Reader, List<Evaluable>> {
    @Override
    public List<Evaluable> apply(Reader sourceCode) {
      try {
        BnlLexer lexer = new BnlLexer(CharStreams.fromReader(sourceCode));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        BnlParser parser = new BnlParser(tokenStream);
        BnlToAbsynVisitor visitor = new BnlToAbsynVisitor();
        Absyn absyn = visitor.visit(parser.prog());
        return absyn.accept(AbsynToExprList.getInstance()).stream().map(EvaluableExpression::new)
                .collect(Collectors.toList());
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to parse test program.");
      }
    }
  }
}
