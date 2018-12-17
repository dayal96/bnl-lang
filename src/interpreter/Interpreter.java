package interpreter;

import expression.IExpression;
import expression.operator.*;
import interpreter.evaluator.IEvaluator;
import interpreter.evaluator.SimpleEvaluator;
import interpreter.parser.IParser;
import interpreter.parser.Parser;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {

  private final Readable input;

  /**
   * Create an Interpreter that reads input from given Readable source.
   * @param input The source of the input.
   */
  public Interpreter(Readable input) {
    this.input = input;
  }

  /**
   * Run the program using given parser and interpreter.
   * @param parser    The parser to use for parsing the input into Evaluables.
   * @param evaluator The evaluator to use for evaluating Evaluables.
   * @throws Exception  If something goes wrong with the Parser or the Evaluator.
   */
  public void runProgram(IParser parser, IEvaluator evaluator) throws Exception {
    List<IEvaluable> toEval = parser.parseEvaluables(this.input);
    evaluator.evaluateProgram(toEval);
  }

  public static void main(String[] args) throws Exception {

    Map<String, IExpression> primitiveOperations = new HashMap<>();
    primitiveOperations.put("+", new Add());
    primitiveOperations.put("-", new Subtract());
    primitiveOperations.put("/", new Divide());
    primitiveOperations.put("*", new Multiply());
    primitiveOperations.put("=", new Equals());
    primitiveOperations.put("if", new Conditional());

    String program = "(define FIVE 5)\n"
        + "(define FOUR (- FIVE 1))\n"
        + "FOUR\n"
        + "(define size-num (lambda (x) (if (= x 0) 1 (* 2 (size-num (- x 1))))))\n"
        + "(size-num 30)\n"
        + "(size-num FOUR)";

    IParser parser = new Parser(primitiveOperations.keySet());
    IEvaluator evaluator = new SimpleEvaluator(primitiveOperations);
    Interpreter interpreter = new Interpreter(new StringReader(program));
    interpreter.runProgram(parser, evaluator);
  }
}
