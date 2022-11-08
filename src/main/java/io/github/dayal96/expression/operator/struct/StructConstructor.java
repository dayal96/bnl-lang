package io.github.dayal96.expression.operator.struct;

import io.github.dayal96.environment.Environment;
import io.github.dayal96.expression.Expression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.struct.StructObject;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.expression.type.NilType;
import io.github.dayal96.expression.type.StructType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StructConstructor extends AOperator {

  public final StructType structType;

  public StructConstructor(String name, int fields) {
    var types = new ArrayList<IType>(fields);
    for (int i = 0; i < fields; i++) {
      types.add(NilType.NIL);
    }
    this.structType = new StructType(name, types);
  }

  @Override
  public Expression evaluate(List<Expression> operands, Environment environment) throws Exception {

    if (operands.size() != structType.fields.size()) {
      throw new Exception("make-" + structType.name + " : expected " + structType.fields.size()
          + " arguments, found " + operands.size());
    }

    List<Expression> evaluated = new LinkedList<>();
    for (var op : operands) {
      evaluated.add(op.evaluate(environment));
    }
    return new StructObject(this.structType, evaluated);
  }

  @Override
  public String toString() {
    return "make-" + structType.name;
  }
}
