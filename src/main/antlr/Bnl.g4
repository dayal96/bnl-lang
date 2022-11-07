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

decl : OPAREN DEFINE ID expr CPAREN ;

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

lambda : OPAREN LAMBDA OPAREN idlist CPAREN expr CPAREN ;

idlist : idlist ID
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


DEFINE     : 'define'                  ;
LAMBDA     : 'lambda' | 'λ'            ;
Newline    : [\r\n]+                   -> skip;
Whitespace : [ \r\n\t\f]+              -> skip;
FALSE      : '#f' | 'false'            ;
TRUE       : '#t' | 'true'             ;
IF         : 'if'                      ;
NUMBER     : [-]?[0-9]+('/'[0-9]+)?    ;
ID         : [a-zA-Z][.a-zA-Z0-9\-]*   ;
STRING     : '"'~[\r\n"]*'"'           ;

OPAREN     : '('  ;
CPAREN     : ')'  ;
PLUS       : '+'  ;
MINUS      : '-'  ;
MULTIPLY   : '*'  ;
DIVIDE     : '/'  ;
EQUALS     : '='  ;
LT         : '<'  ;
GT         : '>'  ;
LEQ        : '<=' ;
GEQ        : '>=' ;

BLOCK_COMMENT  : '#|' .*? '|#'               -> skip;
COMMENT        : '#' ~[\r\n]*                -> skip;
