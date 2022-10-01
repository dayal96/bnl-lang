package io.github.dayal96.primitive;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.IExpression;
import java.util.List;
import org.junit.Test;

public abstract class TestPrimitive {

  protected IExpression sample;

  protected TestPrimitive(IExpression sample) {
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
