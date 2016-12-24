import java.io.StringReader;

import Expression.Expression;
import Parser.Parser;

/**
 * Main class that runs the interpreter.
 */
public class Main {

  public static void main(String[] args) {

    System.out.println("");

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
