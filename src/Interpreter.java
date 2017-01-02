import java.io.StringReader;

import Environment.IEnvironment;
import Environment.SymbolTable;
import Expression.IExpression;
import Parser.Parser;

/**
 * Interpreter class that runs the interpreter.
 */
public class Interpreter {

  public static void main(String[] args) {

    System.out.println("Interpreter for ANL, ver 0.03 with basic arithmetic support and"
            + " constant definitions.\n");
    IEnvironment environment = new SymbolTable();

    if (args[0].equals("text")) {
      Parser parser = new Parser(new StringReader(args[1]), environment);
      for (IExpression e : parser.parseCode()) {
        System.out.println(e.evaluate().toString());
      }
    }
    else {
      System.out.println("Files not supported yet. Check back later!");
    }
  }
}
