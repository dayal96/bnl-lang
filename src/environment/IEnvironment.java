package environment;

import expression.IExpression;

/**
 * Interface to represent an IEnvironment with function and constant definitions.
 */
public interface IEnvironment {

  /**
   * Get the expression associated with the given symbol.
   * @param symbol  the symbol to be searched.
   * @return the IExpression associated with the symbol.
   */
  public IExpression getEntry(String symbol) throws Exception;

  /**
   * Check whether the given symbol is present in the environment.
   * @param symbol  the symbol to be searched.
   * @return true if the symbol is defined in the environment, false otherwise.
   */
  public boolean isPresent(String symbol);

  /**
   * Define a new symbol in the environment.
   * @param symbol  the symbol to be added.
   * @param exp     the IExpression to be associated with the symbol.
   */
  public void addEntry(String symbol, IExpression exp);

  /**
   * Does this IEnvironment have a local component?
   * @return True if there is a local context in the environment, false otherwise.
   */
  public boolean hasLocal();

  /**
   * Get the local context in the environment.
   * @return  The IEnvironment that represents the local context of the environment.
   */
  public IEnvironment getLocal() throws Exception;

  /**
   * Get present working directory.
   */
  public String getWorkingDirectory();

  /**
   * Set present working directory to given working directory.
   */
  public void setWorkingDirectory(String directory);
}
