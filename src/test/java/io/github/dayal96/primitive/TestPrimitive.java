package io.github.dayal96.primitive;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.environment.SymbolTable;
import java.util.List;
import org.junit.Test;

public abstract class TestPrimitive {

  protected Primitive sample;

  protected TestPrimitive(Primitive sample) {
    this.sample = sample;
  }

  @Test
  public void testUseAsFunction() {
    IEnvironment environment = new SymbolTable();

    try {
      sample.evaluate(List.of(), environment);
    } catch (Exception e) {
      assert true;
      return;
    }

    assert false;
  }
}
