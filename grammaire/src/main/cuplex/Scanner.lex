/*
Scanner pour le parser d'expression de constantes
TD Compil L3 Miage
Azim Roussanaly
(c) Universite de Lorraine
2020
*/
package generated.fr.ul.miage.grammaire;
import java_cup.runtime.Symbol;

%%
/* options */
%line
%public
%cupsym Sym
%cup

/* macros */
SEP        =    \s
NUM        =    [0-9]+
COM        =    "//"[^\n]*\n
IDF		= [a-zA-Z_]\w*

%%
/* regles */
"+"            { return new Symbol(Sym.ADD);}
"-"            { return new Symbol(Sym.SUB);}
"*"            { return new Symbol(Sym.MUL);}
"/"            { return new Symbol(Sym.DIV);}
"("            { return new Symbol(Sym.PO);}
")"            { return new Symbol(Sym.PF);}
"{"            { return new Symbol(Sym.AO);}
"}"            { return new Symbol(Sym.AF);}
"="            { return new Symbol(Sym.AFF);}
";"            { return new Symbol(Sym.FINLIGNE);}
","            { return new Symbol(Sym.SEPARATEURPARAM);}
">"            { return new Symbol(Sym.SUP);}
">="            { return new Symbol(Sym.SUPE);}
"<"            { return new Symbol(Sym.INF);}
"<"            { return new Symbol(Sym.INFE);}
"=="            { return new Symbol(Sym.EGAL);}
"!="            { return new Symbol(Sym.DIFFERENT);}
"entier"       { return new Symbol(Sym.ENTIER);}
"fonction"     { return new Symbol(Sym.FONCTION);}
"void"         { return new Symbol(Sym.TYPE);}
"output"       { return new Symbol(Sym.OUTPUT);}
"input"        { return new Symbol(Sym.INPUT);}
"retourner"    { return new Symbol(Sym.RETURN);}
"tq"           { return new Symbol(Sym.TQ);}
"si"           { return new Symbol(Sym.IF);}
"sinon"           { return new Symbol(Sym.ELSE);}

{NUM}          { return new Symbol(Sym.NUM, new Integer(yytext()));}
{SEP}          {;}
{COM}          {;}
{IDF}		   { return new Symbol(Sym.IDF, yytext()); }
<<EOF>>        {return new Symbol(Sym.EOF);}