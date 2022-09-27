package io.github.dayal96.environment;

import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.operator.Conditional;
import io.github.dayal96.expression.operator.Equals;
import io.github.dayal96.expression.operator.cons.Cons;
import io.github.dayal96.expression.operator.cons.First;
import io.github.dayal96.expression.operator.cons.Rest;
import io.github.dayal96.expression.operator.number.Add;
import io.github.dayal96.expression.operator.number.Divide;
import io.github.dayal96.expression.operator.number.GreaterThan;
import io.github.dayal96.expression.operator.number.LessThan;
import io.github.dayal96.expression.operator.number.Multiply;
import io.github.dayal96.expression.operator.number.Subtract;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to represent the environment as a Symbol Table that is not mutable.
 */
public class SymbolTable implements IEnvironment {

  private Map<String, IExpression> table;
  private String workingDirectory;

  public static SymbolTable getPrimitiveOperations() {
    SymbolTable primOps = new SymbolTable();
    primOps.addEntry("+", new Add());
    primOps.addEntry("-", new Subtract());
    primOps.addEntry("/", new Divide());
    primOps.addEntry("*", new Multiply());
    primOps.addEntry("=", new Equals());
    primOps.addEntry("<", new LessThan());
    primOps.addEntry(">", new GreaterThan());
    primOps.addEntry("if", new Conditional());
    primOps.addEntry("cons", new Cons());
    primOps.addEntry("first", new First());
    primOps.addEntry("rest", new Rest());
    return primOps;
  }

  /**
   * Creates a new SymbolTable.
   */
  public SymbolTable() {
    this.table = new TreeMap<>();
    this.workingDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
  }

  @Override
  public IExpression getEntry(String symbol) throws Exception {

    if (!this.isPresent(symbol)) {
      throw new Exception("Symbol not found.");
    }

    return this.table.get(symbol);
  }

  @Override
  public boolean isPresent(String symbol) {
    return this.table.containsKey(symbol);
  }

  @Override
  public void addEntry(String symbol, IExpression exp) {
    this.table.put(symbol, exp);
  }

  @Override
  public String getWorkingDirectory() {
    return this.workingDirectory;
  }

  @Override
  public void setWorkingDirectory(String directory) {
    this.workingDirectory = directory;
  }
}
