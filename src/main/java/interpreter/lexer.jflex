package interpreter;
import java_cup.runtime.*;

%%
%class Lexer
%unicode
%cupsym CupParserSym
%cup
%line
%column

Newline = [\r\n]+
Whitespace = [ \r\n\t\f]+
Number = [-]?[0-9]+(\/[0-9]+)?
Identifier = [a-zA-Z][a-zA-Z0-9\.\-]*
False = #f | false
True = #t | true
If = if
Define = define
Lambda = lambda

%{
  StringBuffer string = new StringBuffer();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

%state LINE_COMMENT BLOCK_COMMENT


%%

<YYINITIAL> {
  "#|"                     { yybegin(BLOCK_COMMENT); }
  "#"                      { yybegin(LINE_COMMENT); }
  "("                      { return symbol(CupParserSym.OPAREN); }
  ")"                      { return symbol(CupParserSym.CPAREN); }
  "["                      { return symbol(CupParserSym.OBRACKET); }
  "]"                      { return symbol(CupParserSym.CBRACKET); }
  "{"                      { return symbol(CupParserSym.OBRACE); }
  "}"                      { return symbol(CupParserSym.CBRACE); }
  "+"                      { return symbol(CupParserSym.PLUS); }
  "-"                      { return symbol(CupParserSym.MINUS); }
  "*"                      { return symbol(CupParserSym.MULTIPLY); }
  "/"                      { return symbol(CupParserSym.DIVIDE); }
  "="                      { return symbol(CupParserSym.EQUALS); }
  ">"                      { return symbol(CupParserSym.GT); }
  "<"                      { return symbol(CupParserSym.LT); }
  ">="                     { return symbol(CupParserSym.GEQ); }
  "<="                     { return symbol(CupParserSym.LEQ); }
  {False}                  { return symbol(CupParserSym.FALSE); }
  {True}                   { return symbol(CupParserSym.TRUE); }
  {If}                     { return symbol(CupParserSym.IF); }
  {Define}                 { return symbol(CupParserSym.DEFINE); }
  {Lambda}                 { return symbol(CupParserSym.LAMBDA); }
  {Identifier}             { return symbol(CupParserSym.ID, yytext()); }
  {Number}                 { return symbol(CupParserSym.NUMBER, yytext()); }
  {Whitespace}             {  }
  [^]                      { throw new Error("Illegal character ["+yytext()+"];"); }
}

<BLOCK_COMMENT> {
  "|#"                     { yybegin(YYINITIAL); }
  .                        {  }
}

<LINE_COMMENT> {
  {Newline}                { yybegin(YYINITIAL); }
  .                        {  }
}
