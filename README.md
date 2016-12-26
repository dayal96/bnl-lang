# my-lang
ANL (stands for ANL is not Lisp) is a functional Lisp-like language that I am working on. As a project, it serves no purpose other than for me to get a deeper understanding of programming languages. In function it is similar to a language I learned in freshman year in college called Basic Student Language, refered to in "How To Design Programs", a book on designing functional programs. This software is licensed under MIT License, a copy of which is available as "LICENSE.txt". You can use this code freely as long as you follow the terms of MIT License.

Changelog:

Version 0.02
- Support for Operators with variable number of operands. The currently supported operators require at least 2 operands.
- E.G. : (+ 1 2 3 4)

Version 0.01
- Support for basic Rational arithmetic.
- You can use Expressions with two variables and one operator.
- Expressions: (+ 1 2), (- (/ 7 5) (\* 3 2))
- Usage: To use the interpreter, compile the code into an executable jar (say "ANL-Interpreter.jar") and run
         java -jar ANL-Interpreter.jar text <expressions>
- All expressions will be evaluated and result printed to standard out.
- Evaluating expressions from a file is not supported for now.
