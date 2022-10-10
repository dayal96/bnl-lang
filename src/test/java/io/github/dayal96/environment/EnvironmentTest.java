package io.github.dayal96.environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import io.github.dayal96.exceptions.ArithmeticError;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.primitive.number.Rational;
import io.github.dayal96.expression.operator.number.Add;
import org.junit.Test;

public abstract class EnvironmentTest {

  private final Environment environment;

  /**
   * Create an EnvironmentTest with given Environment to test.
   *
   * @param environment The environment to test with.
   */
  protected EnvironmentTest(Environment environment) {
    this.environment = environment;
  }

  @Test
  public void testEnvironment() throws Exception {
    try {

      assertFalse(this.environment.isPresent("TWO"));
      Expression exp = new Rational(2, 1);
      this.environment.addEntry("TWO", exp);
      assertTrue(this.environment.isPresent("TWO"));
      assertFalse(this.environment.isPresent("two"));
      assertFalse(this.environment.isPresent("TW"));
      assertEquals(exp, this.environment.getEntry("TWO"));

      Expression plus = new Add();
      assertFalse(this.environment.isPresent("PLUS"));
      this.environment.addEntry("PLUS", plus);
      assertTrue(this.environment.isPresent("PLUS"));
      assertEquals(plus, this.environment.getEntry("PLUS"));
    } catch (ArithmeticError e) {
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
    } catch (Exception e) {
      assert true;
      return;
    }

    assert false;
  }
}
