import java.util.ArrayList;
import java.util.List;

public class TDS {

    private ArrayList<Symboles> listSym;

    public TDS(ArrayList<Symboles> listSym) {
        this.listSym = listSym;
    }

    public ArrayList<Symboles> getListSym() {
        return listSym;
    }

    public void setListSym(ArrayList<Symboles> listSym) {
        this.listSym = listSym;
    }

    public void afficherTDS(){
        StringBuilder TDS = new StringBuilder();

        for (int i = 0; i < listSym.size(); i++) {
            Symboles s = listSym.get(i);
        }
        for(Symboles s: this.listSym){
            TDS.append(s.toTDS()).append("\n");
        }
        System.out.println(TDS);
    }

    public Symboles getSymByName(String name){
        for(Symboles s : this.listSym){
            if(s.getNom().equalsIgnoreCase(name)){
                return s;
            }
        }
        return null;
    }

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
