package environment;

import expression.operator.Add;
import expression.operator.Divide;
import expression.operator.Multiply;
import expression.operator.Subtract;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import expression.IExpression;

/**
 * Class to represent the environment as a Symbol Table that is not mutable.
 */
public class SymbolTable implements IEnvironment {

  private Map<String, IExpression> table;
  private String workingDirectory;

  /**
   * Creates a new SymbolTable.
   */
  public SymbolTable() {
    this.table = new HashMap<>();
    this.workingDirectory = Paths.get(".").toAbsolutePath().normalize().toString();

    // Add some standard functions
    this.addEntry("+", new Add());
    this.addEntry("-", new Subtract());
    this.addEntry("*", new Multiply());
    this.addEntry("/", new Divide());
  }

  @Override
  public void setWorkingDirectory(String directory) {
    this.workingDirectory = directory;
  }

  @Override
  public IExpression getEntry(String symbol) {
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
}
