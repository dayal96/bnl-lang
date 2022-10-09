package io.github.dayal96.primitive;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.Expression;
import java.util.List;
import org.junit.Test;

public abstract class TestPrimitive {

  protected Expression sample;

  protected TestPrimitive(Expression sample) {
    this.sample = sample;
  }

  @Test
  public void testUseAsFunction() {
    Environment environment = new SymbolTable();

    try {
      sample.evaluate(List.of(), environment);
    } catch (Exception e) {
      assert true;
      return;
    }

    assert false;
  }
}
