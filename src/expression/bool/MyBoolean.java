package expression.bool;

import environment.IEnvironment;
import expression.IExpression;
import expression.type.Type;
import java.util.List;

public class MyBoolean implements IExpression {

  public static final MyBoolean TRUE = new MyBoolean(true);
  public static final MyBoolean FALSE = new MyBoolean(false);
  private final boolean value;
  /**
   * Create a Boolean value.
   *
   * @param value The boolean value, one of true and false.
   */
  private MyBoolean(boolean value) {
    this.value = value;
  }

  /**
   * Return the MyBoolean equivalent of the given boolean.
   *
   * @param toMimic The boolean whose equivalent has to be produced.
   * @return The MyBoolean that represents the boolean provided.
   */
  public static MyBoolean of(boolean toMimic) {
    return toMimic ? TRUE : FALSE;
  }

  @Override
  public IExpression evaluate(IEnvironment environment) throws Exception {
    return this;
  }

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    throw new Exception("Cannot evaluate a boolean like a function.");
  }

  @Override
  public Type getType() {
    return Type.BOOLEAN;
  }

  @Override
  public String toString() {
    return Boolean.toString(this.value);
  }

  /**
   * Determine the truth of this MyBoolean.
   *
   * @return True if this boolean is true, false otherwise.
   */
  public boolean truth() {
    return this.equals(TRUE);
  }
}
