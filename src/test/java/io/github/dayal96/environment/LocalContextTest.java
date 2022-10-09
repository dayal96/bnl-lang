package io.github.dayal96.environment;

import static org.junit.Assert.assertEquals;

import io.github.dayal96.expression.Expression;
import io.github.dayal96.primitive.number.Rational;
import org.junit.Test;

/**
 * Class to test LocalContext environments.
 */
public class LocalContextTest extends EnvironmentTest {

  /**
   * Set up the Environment tests with a LocalContext.
   */
  public LocalContextTest() {
    super(new LocalContext(new SymbolTable(), new SymbolTable()));
  }

  @Test
  public void testLocalContexting() throws Exception {
    Environment global = new SymbolTable();

    Expression one = new Rational(1, 1);
    Expression two = new Rational(2, 1);
    Expression three = new Rational(3, 1);
    Expression four = new Rational(4, 1);
    Expression five = new Rational(5, 1);
    Expression six = new Rational(6, 1);

    global.addEntry("ONE", one);
    global.addEntry("TWO", two);
    global.addEntry("THREE", three);

    Environment localized = new LocalContext(global, new SymbolTable());
    assertEquals(one, localized.getEntry("ONE"));
    assertEquals(two, localized.getEntry("TWO"));
    assertEquals(three, localized.getEntry("THREE"));

    localized.addEntry("ONE", four);
    assertEquals(four, localized.getEntry("ONE"));
    assertEquals(two, localized.getEntry("TWO"));
    assertEquals(three, localized.getEntry("THREE"));

    localized.addEntry("TWO", five);
    assertEquals(four, localized.getEntry("ONE"));
    assertEquals(five, localized.getEntry("TWO"));
    assertEquals(three, localized.getEntry("THREE"));

    localized.addEntry("THREE", six);
    assertEquals(four, localized.getEntry("ONE"));
    assertEquals(five, localized.getEntry("TWO"));
    assertEquals(six, localized.getEntry("THREE"));

    assertEquals(one, global.getEntry("ONE"));
    assertEquals(two, global.getEntry("TWO"));
    assertEquals(three, global.getEntry("THREE"));
  }
}
