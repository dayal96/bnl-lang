package io.github.dayal96.expression.operator.string;

import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.PrimType;
import io.github.dayal96.primitive.number.Rational;
import io.github.dayal96.primitive.string.MyString;
import java.util.LinkedList;
import java.util.List;

public class Substring extends AOperator {

  @Override
  public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
      throws Exception {
    if (operands.size() != 3) {
      throw new Exception("substring : expected 3 argument, found " + operands.size());
    }

    List<IExpression> evaluated = new LinkedList<>();

    for (var operand : operands) {
      evaluated.add(operand.evaluate(environment));
    }

    PrimType.NUMBER.join(evaluated.get(0).getType());
    PrimType.NUMBER.join(evaluated.get(1).getType());
    PrimType.STRING.join(evaluated.get(2).getType());

    Rational start = (Rational) evaluated.get(0);
    Rational size = (Rational) evaluated.get(1);
    String str = evaluated.get(2).toString();

    Rational stringLength = new Rational(str.length());

    if (start.add(size).compareTo(stringLength) > 0) {
      throw new IllegalArgumentException("substring : Length of string exceeded.");
    }

    int startIndex = start.number.numerator / start.number.denominator;
    int endIndex = startIndex + (size.number.numerator / size.number.denominator);

    return new MyString(str.substring(startIndex, endIndex));
  }

  @Override
  public String toString() {
    return "substring";
  }
}

