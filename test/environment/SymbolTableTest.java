package environment;

import org.junit.Test;

public class SymbolTableTest extends AEnvironmentTest {

  /**
   * Create an AEnvironmentTest with given IEnvironment to test.
   */
  public SymbolTableTest() {
    super(new SymbolTable());
  }
}
