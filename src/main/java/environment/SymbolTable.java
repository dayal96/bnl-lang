package environment;

import expression.IExpression;
import expression.operator.Conditional;
import expression.operator.Equals;
import expression.operator.cons.Cons;
import expression.operator.cons.First;
import expression.operator.cons.Rest;
import expression.operator.number.Add;
import expression.operator.number.Divide;
import expression.operator.number.GreaterThan;
import expression.operator.number.LessThan;
import expression.operator.number.Multiply;
import expression.operator.number.Subtract;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to represent the environment as a Symbol Table that is not mutable.
 */
public class SymbolTable implements IEnvironment {

  private Map<String, IExpression> table;
  private String workingDirectory;

  public static final SymbolTable primitiveOperations = new SymbolTable();

  static {
    primitiveOperations.addEntry("+", new Add());
    primitiveOperations.addEntry("-", new Subtract());
    primitiveOperations.addEntry("/", new Divide());
    primitiveOperations.addEntry("*", new Multiply());
    primitiveOperations.addEntry("=", new Equals());
    primitiveOperations.addEntry("<", new LessThan());
    primitiveOperations.addEntry(">", new GreaterThan());
    primitiveOperations.addEntry("if", new Conditional());
    primitiveOperations.addEntry("cons", new Cons());
    primitiveOperations.addEntry("first", new First());
    primitiveOperations.addEntry("rest", new Rest());
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
