package io.github.dayal96.expression.local;

import io.github.dayal96.environment.Environment;

public interface Definition {
  void addDefinitionNames(Environment environment) throws Exception;

  void addDefinitionBodies(Environment environment)  throws Exception;
}
