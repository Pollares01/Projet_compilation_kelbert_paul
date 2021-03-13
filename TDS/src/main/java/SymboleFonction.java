package main.java;

public class SymboleFonction extends Symboles {

    private String nbParam, nbBloc;


    public SymboleFonction(String nom, String type, String cat, String nbParam, String nbNloc) {
        super(nom, type, cat);
        this.nbParam = nbParam;
        this.nbBloc = nbNloc;
    }

    public String getNbParam() {
        return nbParam;
    }

    public void setNbParam(String nbParam) {
        this.nbParam = nbParam;
    }

    public String getNbBloc() {
        return nbBloc;
    }

    public void setNbBloc(String nbBloc) {
        this.nbBloc = nbBloc;
    }

    @Override
    public String toString() {
        return "{" +
                "nom='" + nom +
                ", type='" + type +
                ", cat='" + cat +
                ", nbParam='" + nbParam +
                ", nbBloc='" + nbBloc +
                '}';
    }
}
