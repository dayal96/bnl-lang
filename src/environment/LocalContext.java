package environment;

import expression.IExpression;

public class LocalContext implements IEnvironment {

  private IEnvironment global;
  private IEnvironment local;

  /**
   * Create a Local Context which is a combination of the global context with some additional local bindings.
   * @param global  The global context.
   * @param local   The local context.
   */
  public LocalContext(IEnvironment global, IEnvironment local) {
    this.global = global;
    this.local = local;
  }

  @Override
  public IExpression getEntry(String symbol) throws Exception {
    if (this.local.isPresent(symbol)) {
      return this.local.getEntry(symbol);
    }
    else if (this.global.isPresent(symbol)) {
      return this.global.getEntry(symbol);
    }

    throw new Exception("No such symbol : " + symbol);
  }

  @Override
  public boolean isPresent(String symbol) {
    return this.local.isPresent(symbol) || this.global.isPresent(symbol);
  }

  @Override
  public void addEntry(String symbol, IExpression exp) {
    this.local.addEntry(symbol, exp);
  }

  @Override
  public boolean hasLocal() {
    return true;
  }

  @Override
  public IEnvironment getLocal() throws Exception {
    return this.local;
  }

  @Override
  public String getWorkingDirectory() {
    return this.local.getWorkingDirectory();
  }

  @Override
  public void setWorkingDirectory(String directory) {
    this.local.setWorkingDirectory(directory);
  }
}
