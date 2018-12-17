package interpreter.parser;

import environment.IEnvironment;
import interpreter.IEvaluable;

import java.util.List;

public interface IParser {

  /**
   * Parse code in string format, retuning expressions to evaluate and updating environment with definitions.
   * @param input       The readable input source for the source code.
   * @return  The ordered list of expressions to evaluate.
   * @throws Exception  If there are syntax or logical errors in the code.
   */
  List<IEvaluable> parseEvaluables(Readable input) throws Exception;
}
