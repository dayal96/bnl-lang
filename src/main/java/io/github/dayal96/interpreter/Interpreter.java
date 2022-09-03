package io.github.dayal96.interpreter;

import io.github.dayal96.absyn.IAbsyn;
import io.github.dayal96.absyn.transform.AbsynToExprList;
import io.github.dayal96.antlr.BnlLexer;
import io.github.dayal96.antlr.BnlParser;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.interpreter.evaluator.IEvaluator;
import io.github.dayal96.interpreter.evaluator.SimpleEvaluator;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Interpreter {

  private IEvaluator evaluator;

  /**
   * Initialise an interpreter to interpret a program in the given base environment.
   * @param evaluator  The {@link IEvaluator} that evaluates the program.
   */
  public Interpreter(IEvaluator evaluator) {
    this.evaluator = evaluator;
  }


  public void interpret(Reader sourceCode) throws Exception {
    BnlLexer lexer = new BnlLexer(CharStreams.fromReader(sourceCode));
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    BnlParser parser = new BnlParser(tokenStream);
    BnlToAbsynVisitor visitor = new BnlToAbsynVisitor();
    IAbsyn absyn = visitor.visit(parser.prog());

    List<IEvaluable> program =
        absyn.accept(AbsynToExprList.getInstance()).stream().map(EvaluableExpression::new)
            .collect(Collectors.toList());
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
