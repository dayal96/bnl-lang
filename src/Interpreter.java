import expression.operator.Add;
import expression.operator.Divide;
import expression.operator.Multiply;
import expression.operator.Subtract;
import java.io.*;
import java.util.Scanner;

import environment.IEnvironment;
import environment.SymbolTable;
import expression.IExpression;
import parser.IParser;
import parser.Parser;

import static parser.ParseUtils.isCloseParenth;
import static parser.ParseUtils.isOpenParenth;
import static parser.ParseUtils.isWhitespace;

/**
 * Interpreter class that runs the interpreter.
 */
public class Interpreter {

  private IEnvironment environment;

  /**
   * Create an Interpreter.
   * @param environment the symbol table that represents the environment.
   */
  private Interpreter(IEnvironment environment) {
    this.environment = environment;
  }

  private void startConsole(Readable input) {

    Scanner scanner = new Scanner(input);
    scanner.useDelimiter("");

    // Parse all code into Strings with only expressions.
    StringBuilder currentExpression = new StringBuilder();
    int numOpen = 0;

    while (scanner.hasNext()) {
      String currentChar = scanner.next();

      if (isOpenParenth(currentChar)) {
        numOpen++;
        currentExpression.append(currentChar);
      }
      else if (isCloseParenth(currentChar)) {
        numOpen--;
        currentExpression.append(currentChar);

        if (numOpen == 0) {
          this.executeExpression(currentExpression.toString().trim());
          currentExpression = new StringBuilder();
        }
      }
      else if (numOpen == 0 && isWhitespace(currentChar)) {
        if (currentExpression.toString().trim().length() > 0) {
          this.executeExpression(currentExpression.toString().trim());
          currentExpression = new StringBuilder();
        }
      }
      else {
        currentExpression.append(currentChar);
      }
    }
  }

  /**
   * Execute given expression as a String.
   * @param expression the expression to execute, in raw String form.
   */
  private void executeExpression(String expression) {

    IParser parser = new Parser(new StringReader(expression), this.environment);
    for (IExpression e : parser.parseCode()) {
      System.out.println(e.evaluate().toString());
    }

    System.out.print(">");
  }

  public static void main(String[] args) {

    System.out.println("Interpreter for ANL, ver 0.04 with basic arithmetic support and"
        + " constant definitions and a live console.");
    IEnvironment environment = new SymbolTable();

    for (String str : args) {
      try {
        File filepath = new File(str);
        String workingDirectory = filepath.getAbsolutePath();
        workingDirectory = workingDirectory.substring(0, workingDirectory.lastIndexOf("/"));
        environment.setWorkingDirectory(workingDirectory);

        FileInputStream file = new FileInputStream(str);
        IParser parser = new Parser(new InputStreamReader(file), environment);
        System.out.println("Set working directory to : " + environment.getWorkingDirectory());
        for (IExpression expression : parser.parseCode()) {
          System.out.println(expression.evaluate().toString());
        }
        file.close();
      }
      catch (FileNotFoundException e) {
        System.out.println("File " + str + " not found.");
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }

    Interpreter interpreter = new Interpreter(environment);
    System.out.print("> ");
    interpreter.startConsole(new InputStreamReader(System.in));
    System.out.println("Stop.");
  }
}
