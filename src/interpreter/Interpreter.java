package interpreter;

import expression.IExpression;
import expression.operator.*;
import expression.operator.cons.Cons;
import expression.operator.cons.First;
import expression.operator.cons.Rest;
import expression.operator.number.*;
import interpreter.evaluator.IEvaluator;
import interpreter.evaluator.SimpleEvaluator;
import interpreter.parser.IParser;
import interpreter.parser.Parser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interpreter {

    private final Readable input;

    /**
     * Create an Interpreter that reads input from given Readable source.
     *
     * @param input The source of the input.
     */
    public Interpreter(Readable input) {
        this.input = input;
    }

    /**
     * Run the program using given parser and interpreter.
     *
     * @param parser    The parser to use for parsing the input into Evaluables.
     * @param evaluator The evaluator to use for evaluating Evaluables.
     * @throws Exception If something goes wrong with the Parser or the Evaluator.
     */
    public void runProgram(IParser parser, IEvaluator evaluator) throws Exception {
        List<IEvaluable> toEval = parser.parseEvaluables(this.input);
        evaluator.evaluateProgram(toEval);
    }

    public static void main(String[] args) throws Exception {
        FileReader sourceCode = new FileReader(args[0]);
        Lexer lexer = new Lexer(sourceCode);
        CupParser parser = new CupParser(lexer);
        List<IExpression> exprs = ((List<IExpression>) parser.parse().value);
        System.out.println(exprs);
    }

    public static void main2(String[] args) throws Exception {

        Map<String, IExpression> primitiveOperations = new HashMap<>();
        primitiveOperations.put("+", new Add());
        primitiveOperations.put("-", new Subtract());
        primitiveOperations.put("/", new Divide());
        primitiveOperations.put("*", new Multiply());
        primitiveOperations.put("=", new Equals());
        primitiveOperations.put("<", new LessThan());
        primitiveOperations.put(">", new GreaterThan());
        primitiveOperations.put("if", new Conditional());
        primitiveOperations.put("cons", new Cons());
        primitiveOperations.put("first", new First());
        primitiveOperations.put("rest", new Rest());

        if (args.length != 1) {
            throw new Exception("Please enter the path to the source file to run the interpreter.");
        }

        FileReader sourceCode = new FileReader(args[0]);

        IParser parser = new Parser(primitiveOperations.keySet());
        IEvaluator evaluator = new SimpleEvaluator(primitiveOperations);
        Interpreter interpreter = new Interpreter(sourceCode);
        interpreter.runProgram(parser, evaluator);
    }
}
