package generated.fr.ul.miage.expression;
import java_cup.runtime.Symbol;

%%
/* options */
%line
%public
%cupsym Sym
%cup

/* macros */

SEP        = [ \n\r]
NUM        = [0-9]+
COM        = "//".*\n

%%
/* règles */
"+"        { return new Symbol(Sym.ADD);}
"-"        { return new Symbol(Sym.SUB);}
"*"        { return new Symbol(Sym.MUL);}
"/"        { return new Symbol(Sym.DIV);}
"("        { return new Symbol(Sym.PO);}
")"        { return new Symbol(Sym.PF);}
(NUM)      { return new Symbol(Sym.NUM);}
(SEP)      {;}
(COM)      {;}
<<EOF>>    { return new Symbol(Sym.EOF);}