package io.github.dayal96.environment;

import io.github.dayal96.expression.Expression;

/**
 * Interface to represent an Environment with function and constant definitions.
 */
public interface Environment {

  /**
   * Get the expression associated with the given symbol.
   *
   * @param symbol the symbol to be searched.
   * @return the Expression associated with the symbol.
   */
  public Expression getEntry(String symbol) throws Exception;

  /**
   * Check whether the given symbol is present in the environment.
   *
   * @param symbol the symbol to be searched.
   * @return true if the symbol is defined in the environment, false otherwise.
   */
  public boolean isPresent(String symbol);

  /**
   * Define a new symbol in the environment.
   *
   * @param symbol the symbol to be added.
   * @param exp    the Expression to be associated with the symbol.
   */
  public void addEntry(String symbol, Expression exp);

  /**
   * Get present working directory.
   */
  public String getWorkingDirectory();

  /**
   * Set present working directory to given working directory.
   */
  public void setWorkingDirectory(String directory);
}
