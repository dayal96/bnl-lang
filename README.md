# bnl-lang
BNL (stands for BNL is not Lisp) is a functional Lisp-like language that I am working on. As a project, it serves no purpose other than for me to get a deeper understanding of programming languages. In function it is similar to a language I learned in freshman year in college called Basic Student Language, refered to in "How To Design Programs", a book on designing functional programs. This software is licensed under MIT License, a copy of which is available as "LICENSE.txt". You can use this code freely as long as you follow the terms of MIT License.

Usage: Run the program and provide a filepath for the source code to interpret.

E.g. if you compiled the interpreter into 'bnl.jar', you can use `java -jar bnl-interpreter.jar [path-to-bnl-file]` to run the program described in your bnl file.


UPDATE (29 Dec 2020):
I'm in the process of migrating the parser to JFlex and Java CUP instead of my hand-built parser. This process has not completed yet, so while the project will build it's ability to parse BNL is very limited. However, the main2 method in Interpreter is still hooked into the old parser, so if you wish to build a complete BNL interpreter just swap the current main method in Interpreter with main2.

I intend on completing the new parser before the new year starts, so you can also wait a couple days before playing around with it.

This is hopefully the last time I put a notice like this; in future I will work on a separate branch while the master would be kept 'pure'.


UPDATE (27 Dec 2020):
This language is turing-complete, but verbose. There are some simple programs included in the anllib directory that demonstrate it's current power. I am currently reworking the parser to document the grammar as a CFG and use the same in the parser code.

Anticipated Features:
- More Structures / Complex Data
- Graphics
- File Handling
- Support for Asynchronous programs
- Interpreting one program using multiple interpreters over a network (CNL)

