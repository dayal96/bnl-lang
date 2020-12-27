import java_cup.runtime.*;

%%
%class Lexer
%unicode
%cup
%line
%column

Whitespace = [ \r\n\t\f]+
Digit = [0-9]

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}




%%

[Digit]+                                  { return symbol(sym.NUMBER, new Integer(yytext())); }
[^]                                       { throw new Error("Illegal character ["+yytext()+"];"); }
