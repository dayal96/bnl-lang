package io.github.dayal96.environment;

import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.operator.Conditional;
import io.github.dayal96.expression.operator.Equals;
import io.github.dayal96.expression.operator.ListOperator;
import io.github.dayal96.expression.operator.bool.And;
import io.github.dayal96.expression.operator.bool.Or;
import io.github.dayal96.expression.operator.cons.Cons;
import io.github.dayal96.expression.operator.cons.First;
import io.github.dayal96.expression.operator.cons.Rest;
import io.github.dayal96.expression.operator.number.Add;
import io.github.dayal96.expression.operator.number.Divide;
import io.github.dayal96.expression.operator.number.GreaterThan;
import io.github.dayal96.expression.operator.number.IsNumber;
import io.github.dayal96.expression.operator.number.LessThan;
import io.github.dayal96.expression.operator.number.Multiply;
import io.github.dayal96.expression.operator.number.Subtract;
import io.github.dayal96.expression.operator.string.IsString;
import io.github.dayal96.expression.operator.string.NumToString;
import io.github.dayal96.expression.operator.string.StringAppend;
import io.github.dayal96.expression.operator.string.StringLength;
import io.github.dayal96.expression.operator.string.Substring;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class to represent the environment as a Symbol Table that is not mutable.
 */
public class SymbolTable implements Environment {

  private final Map<String, Expression> table;
  private String workingDirectory;

  public static SymbolTable getPrimitiveOperations() {
    List<Expression> operators = List.of(new And(), new Or(),
        new Add(), new Subtract(), new Divide(), new Multiply(),
        new Equals(), new LessThan(), new GreaterThan(), new IsNumber(), new IsString(),
        new NumToString(), new StringAppend() , new StringLength(), new Substring(),
        new Conditional(), new ListOperator(), new Cons(), new First(), new Rest());
    SymbolTable primOps = new SymbolTable();
    operators.forEach((op) -> primOps.addEntry(op.toString(), op));
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
  public Expression getEntry(String symbol) throws Exception {

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
  public void addEntry(String symbol, Expression exp) {
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
