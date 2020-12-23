# bnl-lang
BNL (stands for BNL is not Lisp) is a functional Lisp-like language that I am working on. As a project, it serves no purpose other than for me to get a deeper understanding of programming languages. In function it is similar to a language I learned in freshman year in college called Basic Student Language, refered to in "How To Design Programs", a book on designing functional programs. This software is licensed under MIT License, a copy of which is available as "LICENSE.txt". You can use this code freely as long as you follow the terms of MIT License.

UPDATE (May 2020):
This language is turing-complete, but verbose. There are some simple programs included in the anllib directory that demonstrate it's current power. It is unlikely that I will return attention to this project, but stranger things have happened!

Anticipated Features:
- More Structures / Complex Data
- Interpreting BNL code from files, importing code from other BNL files
- Graphics
- File Handling
- Support for Asynchronous programs


Features in Development:
- Functions

Changelog:

Version 0.03
- Support for defining environment variables with constants. Usage:
    (define IDENTIFIER EXPRESSION)
  Constants can be used in Expressions after they are defined,
  including definitions for other constants.

Version 0.02
- Support for Operators with variable number of operands. The currently supported operators require at least 2 operands.
- E.G. : (+ 1 2 3 4)

Version 0.01
- Support for basic Rational arithmetic.
- You can use Expressions with two operands and one operator.
- Expressions: (+ 1 2), (- (/ 7 5) (\* 3 2))
- Usage: To use the interpreter, compile the code into an executable jar (say "BNL-Interpreter.jar") and run
         java -jar BNL-Interpreter.jar text <expressions>
- All expressions will be evaluated and result printed to standard out.
- Evaluating expressions from a file is not supported for now.
