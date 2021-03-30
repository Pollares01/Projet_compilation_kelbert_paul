//packages et imports
package generated.fr.ul.miage.wc;
%%
//options
%public
%int

//declarations
%{
    /* champ compteur */
    private int n=0;
    /**
     * getter du champ compteur
     * @return le nombre de mots comptés
     */
     public int getN(){
         return n;
     }
%}
%%
//règles
[^ \r\n]+    {n++;}
[^]            {;} //ignorer