# anl-lang
ANL (stands for ANL is not Lisp) is a functional Lisp-like language that I am working on. As a project, it serves no purpose other than for me to get a deeper understanding of programming languages. In function it is similar to a language I learned in freshman year in college called Basic Student Language, refered to in "How To Design Programs", a book on designing functional programs. This software is licensed under MIT License, a copy of which is available as "LICENSE.txt". You can use this code freely as long as you follow the terms of MIT License.

(Some) Terminology:

DATA is anything that is information, from numbers to strings to structures. ANL does not support structures or complex data (yet).
The primitive data types supported by ANL are:
1. Rational Numbers - stored as a pair of integers (numerator-denominator) in reduced form so there are no rounding errors. Overflow may occur due to large numerators/denominators with no common factor. In future Rationals may have arbitrary precision.

CONSTANTS they are fixed values for different data types, for instance, 4 is a rational constant and "word" would be a string constant.

OPERATORS are functions that consume input to produce output. All functions in ANL are operators.

OPERANDS are the data that operators consume. They are synonymous with data, but the term implies "data being used with an operator".

EXPRESSION refers to a valid evaluable expression in anl. An expression consists of an operator and one or more operands. E.G. : (+ 5 7) is an expression in anl.

IDENTIFIERS are names for environment variables. For instance, (define TWO 2) would define an environment variable with the name (or identifier) "TWO" and value as the number 2. Identifiers can not be reused, so (define TWO 4) after (define TWO 2) would throw an error.


Anticipated Features:
- Functions
- Structures / Complex Data
- Ability to import code
- Graphics
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
- Usage: To use the interpreter, compile the code into an executable jar (say "ANL-Interpreter.jar") and run
         java -jar ANL-Interpreter.jar text <expressions>
- All expressions will be evaluated and result printed to standard out.
- Evaluating expressions from a file is not supported for now.
