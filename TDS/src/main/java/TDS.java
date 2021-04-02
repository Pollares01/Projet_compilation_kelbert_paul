package main.java;

import java.util.ArrayList;
import java.util.List;

public class TDS {

    /**
     * liste des symboles d'une TDS
     */
    private ArrayList<Symboles> listSym;

    /**
     * Constructeur d'une TDS
     * @param listSym liste de symboles
     */
    public TDS(ArrayList<Symboles> listSym) {
        this.listSym = listSym;
    }

    /**
     * Getter listSym
     * @return liste de symboles
     */
    public ArrayList<Symboles> getListSym() {
        return listSym;
    }

    /**
     * Affiche une TDS
     * @param tds TDS
     */
    public static void afficherTDS(TDS tds){
        StringBuilder TDS = new StringBuilder();

        for (int i = 0; i < tds.getListSym().size(); i++) {
            Symboles s = tds.getListSym().get(i);
        }
        for(Symboles s: tds.getListSym()){
            TDS.append(s.toTDS()).append("\n");
        }
        System.out.println(TDS);
    }

    /**
     * Getter d'un symbole par son nom
     * @param name nom du symbole recherché
     * @return Symboles
     */
    public Symboles getSymByName(String name){
        for(Symboles s : this.listSym){
            if(s.getNom().equalsIgnoreCase(name)){
                return s;
            }
        }
        return null;
    }

    /**
     * Recherche d'un symbole de onction dans une tds
     * @param name nom de la fonction recherchée
     * @return SymboleFonction
     */
    public SymboleFonction getFonctionByName(String name){
        for(Symboles s : this.listSym){
            if(s instanceof SymboleFonction){
                if(s.getNom().equalsIgnoreCase(name)){
                    return (SymboleFonction) s;
                }
            }
        }
        return null;
    }

    /**
     * Recherche d'un identifiant dans une TDS
     * @param name nom de l'identifiant recherché
     * @return SymboleIdentifiant
     */
    public SymboleIdentifiant getIdfByName(String name){
        for(Symboles s : this.listSym){
            if(s instanceof SymboleIdentifiant){
                if(s.getNom().equalsIgnoreCase(name)){
                    return (SymboleIdentifiant) s;
                }
            }
        }
        return null;
    }

    /**
     * Getter de tous les identifiants d'une TDS
     * @return Liste d'identifiants
     */
    public ArrayList<Symboles> getIdentifiant(){
        ArrayList<Symboles> res = new ArrayList<>();
        for(Symboles s : this.listSym){
            if(s instanceof SymboleIdentifiant){
                res.add(s);
            }
        }
        return res;
    }



}
