/*
Scanner pour le parser d'expression de constantes
TD Compil L3 Miage
Azim Roussanaly
(c) Universite de Lorraine
2020
*/
package generated.fr.ul.miage.expression;
import java_cup.runtime.Symbol ;

%%
/* options */
%line
%public
%cupsym Sym
%cup

/* macros */
SEP        =    [ \n\r]
NUM        =    [0-9]+
COM        =    "//"[^\n]*\n

%%
/* regles */
"+"            { return new Symbol(Sym.ADD);}
"-"            { return new Symbol(Sym.SUB);}
"*"            { return new Symbol(Sym.MUL);}
"/"            { return new Symbol(Sym.DIV);}
"("            { return new Symbol(Sym.PO);}
")"            { return new Symbol(Sym.PF);}



{NUM}          { return new Symbol(Sym.NUM, new Integer(yytext()));}
{SEP}          {;}
{COM}          {;}
<<EOF>>        {return new Symbol(Sym.EOF);}