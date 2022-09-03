package io.github.dayal96.expression.type;

/**
 * Interface for representing Types.
 */
public interface IType {
  /**
   * Find a type between this and that which is compatible with both.
   * @param that   The other type to check compatibility against.
   * @return       The {@link IType} that is a common root for both.
   * @throws Exception If the two types are not compatible at all.
   */
  IType join(IType that) throws Exception;
}
