package io.github.dayal96.expression;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;

public class TestComplex {
  private final ProgramTester tester;
  private final Map<String, Optional<String>> tests;

  public TestComplex() {
    this.tester = new ProgramTester();
    this.tests = new LinkedHashMap<String, Optional<String>>();
  }

  @Before
  public void addTests() {
    testMap();
    testLocalNesting();
    testHigherOrderFunc();
    testStrings();
  }

  @Test
  public void testAll() throws Exception {
    this.tester.testPrograms(this.tests);
  }

  private void testMap() {
    String prog =
        "(define empty #f)\n"
        + "(define map \n"
        + "    (lambda (list func)\n"
        + "        (if (= empty list)\n"
        + "            empty\n"
        + "            (cons (func (first list))\n"
        + "                  (map (rest list) func)))))\n"
        + "\n"
        + "(map (cons 1 (cons 2 (cons -1 empty))) (lambda (x) (* x x)))\n";
    String expected = "(cons 1 (cons 4 (cons 1 false)))";
    this.tests.put(prog, Optional.of(expected));
  }

  private void testLocalNesting() {
    String prog =
        "(define empty #f)\n"
            + "(define map \n"
            + "    (lambda (list func)\n"
            + "        (if (= empty list)\n"
            + "            empty\n"
            + "            (define rec (map (rest list) func))"
            + "            (cons (func (first list)) rec))))\n"
            + "\n"
            + "(map (cons 1 (cons 2 (cons -1 empty))) (lambda (x) (* x x)))\n";
    String expected = "(cons 1 (cons 4 (cons 1 false)))";
    this.tests.put(prog, Optional.of(expected));
  }

  private void testHigherOrderFunc() {
    String[] prog =
        {"(define y 7)\n"
            + "((define add-5 (lambda (x) (+ x 5))) add-5 y)",
        "(define make-adder\n"
            + "    (lambda (n)\n"
            + "        (define add-n\n"
            + "            (lambda (x) (+ n x)))\n"
            + "        add-n))\n"
            + "((make-adder 5) 7)",
        "(((lambda (n)\n"
            + "    (define add-n\n"
            + "        (lambda (x) (+ n x)))\n"
            + "    add-n) 5) 7)",
        "(define cfalse (lambda (x y) y))\n"
            + "(define ctrue (lambda (x y) x))\n"
            + "(define cnot (lambda (b) (lambda (x y) (b y x))))\n"
            + "(cons (ctrue true false) ((cnot cfalse) #t #f))"};
    String[] expected = {"12", "12", "12", "(cons true true)"};

    for(int i = 0; i < prog.length; i++) {
      this.tests.put(prog[i], Optional.of(expected[i]));
    }
  }

  private void testStrings() {
    String strings = """
        (define STRING-1 "apple")
        (define STRING-2 "ball")
        (define string-to-cap
          (lambda (str)
            (define len (string-length str))
            (if (= len 0) ""
              (string-append (letter-to-cap (substring 0 1 str))
                             (string-to-cap (substring 1 (- len 1) str))))))
        (define letter-to-cap
          (lambda (str)
            (if (= str "a") "A"
              (if (= str "b") "B"
                (if (= str "e") "E"
                  (if (= str "l") "L"
                    (if (= str "p") "P" str)))))))
        (string-append (string-to-cap STRING-1) ", " (string-to-cap STRING-2))
        """;

    String expected = "\"APPLE, BALL\"";
    this.tests.put(strings, Optional.of(expected));
  }
}
