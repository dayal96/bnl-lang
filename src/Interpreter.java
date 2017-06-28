import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import Environment.IEnvironment;
import Environment.SymbolTable;
import Expression.IExpression;
import Parser.IParser;
import Parser.Parser;

import static Parser.ParseUtils.isCloseParenth;
import static Parser.ParseUtils.isOpenParenth;
import static Parser.ParseUtils.isWhitespace;

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

    System.out.println("Interpreter for ANL, ver 0.10 with basic arithmetic support and"
        + " constant definitions and a live console.\n");
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
    System.out.print(">");
    interpreter.startConsole(new InputStreamReader(System.in));
    System.out.println("Stop.");
  }
}
