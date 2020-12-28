# bnl-lang
BNL (stands for BNL is not Lisp) is a functional Lisp-like language that I am working on. As a project, it serves no purpose other than for me to get a deeper understanding of programming languages. In function it is similar to a language I learned in freshman year in college called Basic Student Language, refered to in "How To Design Programs", a book on designing functional programs. This software is licensed under MIT License, a copy of which is available as "LICENSE.txt". You can use this code freely as long as you follow the terms of MIT License.

Usage: Run the program and provide a filepath for the source code to interpret.

E.g. if you compiled the interpreter into 'bnl.jar', you can use `java -jar bnl-interpreter.jar [path-to-bnl-file]` to run the program described in your bnl file.


UPDATE (Dec 2020):
This language is turing-complete, but verbose. There are some simple programs included in the anllib directory that demonstrate it's current power. I am currently reworking the parser to document the grammar as a CFG and use the same in the parser code.

Anticipated Features:
- More Structures / Complex Data
- Graphics
- File Handling
- Support for Asynchronous programs
- Interpreting one program using multiple interpreters over a network (CNL)

