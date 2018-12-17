package environment;

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
  public boolean hasLocal() {
    return false;
  }

  @Override
  public IEnvironment getLocal() throws Exception {
    throw new Exception("This environment has no local component.");
  }

  @Override
  public String getWorkingDirectory() {
    return this.workingDirectory;
  }
}
