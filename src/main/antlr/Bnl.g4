grammar Bnl;

prog : exprlist ;

exprlist : expr
         | exprlist expr
         ;

expr : localexpr
     | simplexpr
     ;

localexpr : declist simplexpr
          ;

declist : declist decl
        | decl
        ;

decl : OPAREN DEFINE ID expr CPAREN            # valueDecl
     | OPAREN DEFINE_STRUCT ID idlist CPAREN   # structDecl
     ;

simplexpr : prim
          | primop
          | cond
          | lambda
          | funcall
          ;

prim : ID             # primId
     | NUMBER         # primNum
     | STRING         # primString
     | FALSE          # primFalse
     | TRUE           # primTrue
     ;

cond : OPAREN IF expr expr expr CPAREN ;

lambda : OPAREN LAMBDA idlist expr CPAREN ;

idlist : OPAREN idlisteles CPAREN
       | OBRACKET idlisteles CBRACKET;

idlisteles : idlisteles ID
           | ID;

funcall : OPAREN expr exprlist CPAREN ;

primop : PLUS         # plus
       | MINUS        # minus
       | MULTIPLY     # multiply
       | DIVIDE       # divide
       | EQUALS       # equals
       | LT           # lessThan
       | GT           # greaterThan
       | LEQ          # leq
       | GEQ          # geq
       ;


DEFINE         : 'define'                  ;
DEFINE_STRUCT  : 'define-struct'           ;
LAMBDA         : 'lambda' | 'λ'            ;
Newline        : [\r\n]+                   -> skip;
Whitespace     : [ \r\n\t\f]+              -> skip;
FALSE          : '#f' | 'false'            ;
TRUE           : '#t' | 'true'             ;
IF             : 'if'                      ;
NUMBER         : [-]?[0-9]+('/'[0-9]+)?    ;
ID             : [a-zA-Z][.a-zA-Z0-9\-]*   ;
STRING         : '"'~[\r\n"]*'"'           ;

OPAREN     : '('  ;
CPAREN     : ')'  ;
OBRACKET   : '['  ;
CBRACKET   : ']'  ;
PLUS       : '+'  ;
MINUS      : '-'  ;
MULTIPLY   : '*'  ;
DIVIDE     : '/'  ;
EQUALS     : '='  ;
LT         : '<'  ;
GT         : '>'  ;
LEQ        : '<=' ;
GEQ        : '>=' ;

BLOCK_COMMENT  : '#|' .*? '|#'           -> skip;
COMMENT        : '#' ~[ft] ~[\r\n]*      -> skip;
EMPTY_COMMENT  : '#'                     -> skip;
