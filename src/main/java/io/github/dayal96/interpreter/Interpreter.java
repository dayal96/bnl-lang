package io.github.dayal96.interpreter;

import io.github.dayal96.interpreter.evaluator.IEvaluator;
import io.github.dayal96.interpreter.evaluator.SimpleEvaluator;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class Interpreter {

  private IEvaluator evaluator;

  /**
   * Initialise an interpreter to interpret a program in the given base environment.
   * @param evaluator  The {@link IEvaluator} that evaluates the program.
   */
  public Interpreter(IEvaluator evaluator) {
    this.evaluator = evaluator;
  }

  /**
   * Interpret given source source code using the given evaluator.
   * @param sourceCode  The {@link Reader} to read source code from.
   */
  public void interpret(Reader sourceCode) throws Exception {
    Lexer lexer = new Lexer(sourceCode);
    CupParser parser = new CupParser(lexer);
    List<IEvaluable> program = ((List<IEvaluable>) parser.parse().value);
    this.evaluator.evaluateProgram(program);
  }

  public static void main(String[] args) throws Exception {


    if (args.length != 1) {
      throw new Exception("Please enter the path to the source file to run the interpreter.");
    }

    FileReader sourceCode = new FileReader(args[0]);
    IEvaluator evaluator = new SimpleEvaluator();

    Interpreter interpreter = new Interpreter(evaluator);
    interpreter.interpret(sourceCode);
  }
}
