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

prim : ID
     | NUMBER
     | FALSE
     | TRUE
     ;

cond : OPAREN IF expr expr expr CPAREN ;

lambda : OPAREN LAMBDA OPAREN idlist CPAREN expr CPAREN ;

idlist : idlist ID
       | ID;

funcall : OPAREN expr exprlist CPAREN ;

primop : PLUS
       | MINUS
       | MULTIPLY
       | DIVIDE
       | EQUALS
       | LT
       | GT
       | LEQ
       | GEQ
       ;


DEFINE     : 'define'                  ;
LAMBDA     : 'lambda'                  ;
Newline    : [\r\n]+                   -> skip;
Whitespace : [ \r\n\t\f]+              -> skip;
FALSE      : '#f' | 'false'            ;
TRUE       : '#t' | 'true'             ;
IF         : 'if'                      ;
NUMBER     : [-]?[0-9]+('/'[0-9]+)?    ;
ID         : [a-zA-Z][.a-zA-Z0-9\-]*   ;

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


