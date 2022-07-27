package io.github.dayal96.exceptions;

/**
 * Class to represent a Divide by Zero Arithmetic Error.
 */
public class DivideByZeroError extends ArithmeticError {

  public DivideByZeroError(String msg) {
    super(msg);
  }
}
