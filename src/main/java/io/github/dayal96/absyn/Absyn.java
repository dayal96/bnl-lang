package io.github.dayal96.absyn;

public interface Absyn {
  <T> T accept(AbsynVisitor<T> visitor);
}
