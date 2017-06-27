package Environment;

import java.util.HashMap;
import java.util.Map;

import Expression.IExpression;

/**
 * Class to represent the Environment as a Symbol Table that is not mutable.
 */
public class SymbolTable implements IEnvironment {

  private Map<String, IExpression> table;

  /**
   * Creates a new SymbolTable.
   */
  public SymbolTable() {
    this.table = new HashMap<String, IExpression>();
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
}
