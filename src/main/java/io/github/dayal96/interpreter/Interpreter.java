package io.github.dayal96.interpreter;

import io.github.dayal96.absyn.IAbsyn;
import io.github.dayal96.absyn.transform.AbsynToExprList;
import io.github.dayal96.antlr.BnlLexer;
import io.github.dayal96.antlr.BnlParser;
import io.github.dayal96.environment.IEnvironment;
import io.github.dayal96.environment.SymbolTable;
import io.github.dayal96.expression.IExpression;
import io.github.dayal96.expression.operator.AOperator;
import io.github.dayal96.expression.type.IType;
import io.github.dayal96.interpreter.evaluator.IEvaluator;
import io.github.dayal96.interpreter.evaluator.SimpleEvaluator;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Interpreter<T> {

  private final IEvaluator<T> evaluator;

  /**
   * Initialise an interpreter to interpret a program in the given base environment.
   * @param evaluator  The {@link IEvaluator} that evaluates the program.
   */
  public Interpreter(IEvaluator<T> evaluator) {
    this.evaluator = evaluator;
  }


  public T interpret(Reader sourceCode) throws Exception {
    BnlLexer lexer = new BnlLexer(CharStreams.fromReader(sourceCode));
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    BnlParser parser = new BnlParser(tokenStream);
    BnlToAbsynVisitor visitor = new BnlToAbsynVisitor();
    IAbsyn absyn = visitor.visit(parser.prog());

    List<IEvaluable> program =
        absyn.accept(AbsynToExprList.getInstance()).stream().map(EvaluableExpression::new)
            .collect(Collectors.toList());
    return this.evaluator.evaluateProgram(program);
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      throw new Exception("Please enter the path to the source file to run the interpreter.");
    }

    Builder interpreterBuilder = new Builder();
    Interpreter<Void> interpreter = interpreterBuilder.build();
    FileReader sourceCode = new FileReader(args[0]);
    interpreter.interpret(sourceCode);
  }

  public static class Builder {

    private IEnvironment environment;
    private Writer out;
    public Builder() {
      this.environment = SymbolTable.getPrimitiveOperations();
      this.out = new OutputStreamWriter(System.out);
    }

    public Builder setEnvironment(IEnvironment environment) {
      this.environment = environment;
      return this;
    }

    public Builder addToEnvironment(String name, List<IType> signature,
        Function<List<IExpression>, IExpression> function) {
      AOperator newOp = new AOperator() {
        @Override
        public IExpression evaluate(List<IExpression> operands, IEnvironment environment)
            throws Exception {
          if (operands.size() != signature.size()) {
            throw new IllegalArgumentException(String.format("%s : expected %d arguments, found %d", name,
                signature.size(), operands.size()));
          }

          boolean allMatch = true;
          for (int i = 0; i < operands.size(); i++) {
            allMatch = allMatch && (operands.get(i).getType().equals(signature.get(i)));
          }

          if (!allMatch) {
            throw new IllegalArgumentException(String.format("%s : argument type mismatch", name));
          }

          return function.apply(operands);
        }
      };

      this.environment.addEntry(name, newOp);

      return this;
    }

    public Builder outputTo(Writer out) {
      this.out = out;
      return this;
    }

    public Interpreter<Void> build() {
      return new Interpreter<>(new SimpleEvaluator(this.environment, out));
    }
  }
}
