import java.io.StringReader;

import Expression.Expression;
import Parser.Parser;

/**
 * Interpreter class that runs the interpreter.
 */
public class Interpreter {

  public static void main(String[] args) {

    System.out.println("Interpreter for ANL, ver 0.01 with basic arithmetic support.\n");

    if (args[0].equals("text")) {
      Parser parser = new Parser(new StringReader(args[1]));
      for (Expression e : parser.parseCode()) {
        System.out.println(e.evaluate().toString());
      }
    }
    else {
      System.out.println("Files not supported yet. Check back later!");
    }
  }
}
