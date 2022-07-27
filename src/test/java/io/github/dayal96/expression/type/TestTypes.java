package io.github.dayal96.expression.type;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import org.junit.Test;

public class TestTypes {

  @Test
  public void testNilType() throws Exception {
    IType nil = NilType.NIL;
    IType prim1 = PrimType.NUMBER;
    IType prim2 = PrimType.BOOLEAN;
    IType fun1 = new FunctionSignature(Arrays.asList(prim1, prim1), prim1);
    IType struct1 = new StructType(new LinkedList<>());

    for (IType type : Arrays.asList(nil, prim1, prim2, fun1, struct1)) {
      assertEquals(type, nil.join(type));
      assertEquals(type, type.join(nil));
    }
  }

  @Test
  public void testPrimTypes() throws Exception {
    IType type1 = PrimType.NUMBER;
    IType type2 = PrimType.BOOLEAN;

    assertEquals(PrimType.NUMBER, type1.join(type1));
    assertEquals(PrimType.BOOLEAN, type2.join(type2));

    try {
      type1.join(type2);
      assert false;
    }
    catch (Exception e) {
      assert true;
    }

    try {
      type2.join(type1);
      assert false;
    }
    catch (Exception e) {
      assert true;
    }
  }

}
