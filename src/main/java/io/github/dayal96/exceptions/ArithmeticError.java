package io.github.dayal96.exceptions;

/**
 * Represents an Arithmetic Error, like division by 0.
 */
public class ArithmeticError extends Exception {

  /**
   * Creates a new ArithmeticError.
   *
   * @param msg the message to show.
   */
  public ArithmeticError(String msg) {
    super(msg);
  }
}
