# bnl-lang
BNL (stands for BNL is not Lisp, pronounced "banal") is a functional language with Lisp-like 
syntax. As a project, it serves no purpose other than for me to get a deeper understanding
of programming languages. In function, it is similar to a language I learned in freshman
year in college called Basic Student Language, referred to in "How To Design Programs". 
This software license is available as "LICENSE.txt".

Usage: Run the program and provide a filepath for the source code to interpret.

E.g. if you compiled the interpreter into 'bnl.jar', you can use `java -jar bnl.jar 
[path-to-bnl-file]` to run the program described in your bnl file.

UPDATE (2 Sep 2022):
Many software and dependency upgrades were packed into this update. JFlex and JavaCup are 
replaced with ANTLR which requires a smaller grammar definition and is easier to integrate
cleanly with the gradle build process. An intermediate representation for the syntax tree 
(Absyn) is also implemented. This is a necessary step for moving on to the next big planned
feature for BNL - macros. Syntax and semantics for the language are unchanged from
previous version.

UPDATE (14 Jan 2021):
Function scoping issues have been fixed and grammar for local definitions has been added; 
true higher-order functions can now be used in BNL. Some examples for use of higher-order
functions and local definitions have been provided in `bnllib` folder.

UPDATE (1 Jan 2021):
BNL is now a fully functional interpreter for ANL programs with a JFlex lexer and Java CUP 
parser. However, there is a lot more work to do to fix the problems with ANL, like the 
half-baked type system. I've listed a few of the features I'll be adding to BNL that are 
not present in ANL:

1. User-defined structured data
  * For now the system-defined `cons` is the only structure.
2. Type-checking
  * There are no type declarations in ANL but types are inferred
  * BNL will also add grammar for type declarations and function signatures, but they will 
not be required
3. Improved scopes and local definitions
  * Definitions in ANL were only allowed at global level, it's grammar did not allow local 
definitions
4. True higher-order functions
  * ANL grammar only allows function calls via name, due to scoping issues. Fixes for those would 
allow BNL to have true higher-order functions.

Once BNL is complete, the next goal is to develop a distributed interpreter for it - CNL 
(pronounced "canal"), or hook it up as the backend for my English-like language - SIMON 
(coming soon on GitHub).


After BNL:
- Graphics
- File Handling
- Support for Asynchronous programs
- Interpreting one program using multiple interpreters over a network (CNL)

