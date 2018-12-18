package environment;

import static org.junit.Assert.assertEquals;

import exceptions.ArithmeticError;
import expression.IExpression;
import expression.number.Rational;
import expression.operator.Add;
import org.junit.Test;

public abstract class AEnvironmentTest {
  private IEnvironment environment;

  /**
   * Create an AEnvironmentTest with given IEnvironment to test.
   * @param environment The environment to test with.
   */
  protected AEnvironmentTest(IEnvironment environment) {
    this.environment = environment;
  }

  @Test
  public void testEnvironment() throws Exception {
    try {

      assertEquals(false, this.environment.isPresent("TWO"));
      IExpression exp = new Rational(2, 1);
      this.environment.addEntry("TWO", exp);
      assertEquals(true, this.environment.isPresent("TWO"));
      assertEquals(false, this.environment.isPresent("two"));
      assertEquals(false, this.environment.isPresent("TW"));
      assertEquals(exp, this.environment.getEntry("TWO"));

      IExpression plus = new Add();
      assertEquals(false, this.environment.isPresent("PLUS"));
      this.environment.addEntry("PLUS", plus);
      assertEquals(true, this.environment.isPresent("PLUS"));
      assertEquals(plus, this.environment.getEntry("PLUS"));
    }
    catch (ArithmeticError e) {
      assert false;
    }
  }

  @Test
  public void testWorkingDirectory() {
    this.environment.setWorkingDirectory("./");
    assertEquals("./", this.environment.getWorkingDirectory());

    this.environment.setWorkingDirectory("../");
    assertEquals("../", this.environment.getWorkingDirectory());
  }

  @Test
  public void testSymbolNotFound() throws Exception {

    try {
      this.environment.getEntry("lambdas are fun no?");
    }
    catch (Exception e) {
      assert true;
      return;
    }

    assert false;
  }
}
