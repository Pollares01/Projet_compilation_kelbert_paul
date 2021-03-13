package main.java;

public class SymboleFonction extends Symboles {

    public SymboleFonction(String nom, String type, String cat, String nbParam, String nbNloc) {
        super(nom, type, cat);
        this.nbParam = nbParam;
        this.nbBloc = nbNloc;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("{");
        res.append("nom=")
                .append(nom)
                .append(", type=")
                .append(type)
                .append(", cat=")
                .append(cat);
        if(nbParam!=null){
            res.append(", nbParam=").append(nbParam);
        }
        if(nbBloc!=null){
            res.append(", nbBloc=").append(nbBloc);
        }
        res.append("}");
        return res.toString();
    }
}
