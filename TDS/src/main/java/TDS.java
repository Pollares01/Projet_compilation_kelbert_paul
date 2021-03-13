package main.java;

import java.util.ArrayList;

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
        for(Symboles s: listSym){
            TDS.append(s.toString()).append("\n");
        }
        System.out.println(TDS);
    }
}
