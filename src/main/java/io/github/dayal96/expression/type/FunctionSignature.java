package io.github.dayal96.expression.type;

import java.util.LinkedList;
import java.util.List;

/**
 * Class to represent the type of function.
 */
public class FunctionSignature extends NilType {
  private final List<IType> inputs;
  private final IType output;

  /**
   * Create a function signature with the given input and output types.
   * @param inputs  The type for the inputs, in order.
   * @param output  The type for the output.
   */
  public FunctionSignature(List<IType> inputs, IType output) {
    this.inputs = inputs;
    this.output = output;
  }

  @Override
  public IType join(IType that) throws Exception {
    if (that instanceof FunctionSignature) {
      FunctionSignature other = (FunctionSignature) that;
      List<IType> joinedInputs = new LinkedList<>();

      int size = this.inputs.size();
      if (size != other.inputs.size()) {
        return this.mismatch(that);
      }

      try {
        for (int i = 0; i < size; i++) {
          joinedInputs.add(this.inputs.get(i).join(other.inputs.get(i)));
        }
      }
      catch (Exception e) {
        this.mismatch(that);
      }

      IType joinedOutput = this.output.join(other.output);
      return new FunctionSignature(joinedInputs, joinedOutput);
    }
    else if (that.equals(NIL)) {
      return this;
    }
    else {
      return this.mismatch(that);
    }
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder("(");

    for(IType input : this.inputs) {
      str.append(" ").append(input);
    }

    str.append(" -> ").append(this.output).append(" )");
    return str.toString();
  }
}
