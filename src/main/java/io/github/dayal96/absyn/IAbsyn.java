package io.github.dayal96.absyn;

public interface IAbsyn {
  <T> T accept(AbsynVisitor<T> visitor);
}
