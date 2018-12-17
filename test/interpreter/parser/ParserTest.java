package interpreter.parser;

import java.util.List;

public class ParserTest extends AParserTest {

  /**
   * Create a Parser to use for testing.
   */
  public ParserTest() {
    super(new Parser(List.of()));
  }
}
