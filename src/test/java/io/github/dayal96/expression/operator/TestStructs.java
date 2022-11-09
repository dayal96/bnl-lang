package io.github.dayal96.expression.operator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.local.StructDefinition;
import io.github.dayal96.expression.operator.struct.StructAccessor;
import io.github.dayal96.expression.operator.struct.StructConstructor;
import io.github.dayal96.expression.operator.struct.StructPredicate;
import java.util.List;
import org.junit.Test;

public class TestStructs {
  @Test
  public void simpleStruct() throws Exception {
    Environment environment = new SymbolTable();
    StructDefinition struct = new StructDefinition("posn", List.of("x", "y"));

    struct.addDefinitionNames(environment);
    struct.addDefinitionBodies(environment);

    StructConstructor makePosn = new StructConstructor("posn", List.of("x", "y"));
    StructPredicate isPosn = new StructPredicate(makePosn.structType);
    StructAccessor posnX = new StructAccessor(makePosn.structType, "x", 0);
    StructAccessor posnY = new StructAccessor(makePosn.structType, "y", 1);


    assertEquals(makePosn, environment.getEntry("make-posn"));
    assertEquals(isPosn, environment.getEntry("posn?"));
    assertEquals(posnX, environment.getEntry("posn-x"));
    assertEquals(posnY, environment.getEntry("posn-y"));
  }

  @Test
  public void emptyStruct() throws Exception {
    Environment environment = new SymbolTable();
    StructDefinition struct = new StructDefinition("empty", List.of());

    struct.addDefinitionNames(environment);
    struct.addDefinitionBodies(environment);

    StructConstructor makeEmpty = new StructConstructor("empty", List.of());
    StructPredicate isEmpty = new StructPredicate(makeEmpty.structType);

    assertEquals(makeEmpty, environment.getEntry("make-empty"));
    assertEquals(isEmpty, environment.getEntry("empty?"));
  }

  @Test
  public void structsDiffer() throws Exception {
    StructDefinition struct1 = new StructDefinition("struct1", List.of("field1", "field2"));
    StructDefinition struct2 = new StructDefinition("struct2", List.of("field1", "field2"));
    StructDefinition struct1DiffFields = new StructDefinition("struct1", List.of("field0",
        "field1"));

    assertNotEquals(struct1, struct2);
    assertNotEquals(struct2, struct1DiffFields);
    assertNotEquals(struct1, struct1DiffFields);

    Environment env1 = new SymbolTable();
    Environment env2 = new SymbolTable();
    Environment env1DiffFields = new SymbolTable();

    struct1.addDefinitionNames(env1);
    struct1.addDefinitionBodies(env1);

    struct2.addDefinitionNames(env2);
    struct2.addDefinitionBodies(env2);

    struct1DiffFields.addDefinitionNames(env1DiffFields);
    struct1DiffFields.addDefinitionBodies(env1DiffFields);

    List<StructDefinition> structs = List.of(struct1, struct2, struct1DiffFields);
    List<Environment> envs = List.of(env1, env2, env1DiffFields);

    for (int i = 0; i < structs.size(); i++) {
      StructDefinition s1 = structs.get(i);
      Environment e1 = envs.get(i);
      for (int j = i + 1; j < structs.size(); j++) {
        StructDefinition s2 = structs.get(j);
        Environment e2 = envs.get(j);

        try {
          assertNotEquals(e1.getEntry("make-" + s1.name), e2.getEntry("make-" + s2.name));
          assertNotEquals(e1.getEntry(s1.name + "?"), e2.getEntry(s2.name + "?"));

          for (var f1 : s1.fields) {
            for (var f2 : s2.fields) {
              assertNotEquals(e1.getEntry(s1.name + "-" + f1), e2.getEntry(s2.name + "-" + f2));
            }
          }
        } catch (AssertionError e) {
          throw new AssertionError("Comparison failure for : " + s1 + " , " + s2 + "", e);
        }
      }
    }
  }
}
