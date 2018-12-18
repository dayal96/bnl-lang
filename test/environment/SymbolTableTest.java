package environment;

import org.junit.Test;

public class SymbolTableTest extends AEnvironmentTest {

  /**
   * Create an AEnvironmentTest with given IEnvironment to test.
   */
  public SymbolTableTest() {
    super(new SymbolTable());
  }

  @Test (expected = Exception.class)
  public void testGettingLocal() throws Exception {
    IEnvironment environment = new SymbolTable();
    IEnvironment local = environment.getLocal();
  }
}
