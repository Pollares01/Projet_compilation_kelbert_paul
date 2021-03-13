public class SymboleFonction extends Symboles{

    private String nbParam, nbNloc;


    public SymboleFonction(String nom, String type, String cat, String nbParam, String nbNloc) {
        super(nom, type, cat);
        this.nbParam = nbParam;
        this.nbNloc = nbNloc;
    }

    public String getNbParam() {
        return nbParam;
    }

    public void setNbParam(String nbParam) {
        this.nbParam = nbParam;
    }

    public String getNbNloc() {
        return nbNloc;
    }

    public void setNbNloc(String nbNloc) {
        this.nbNloc = nbNloc;
    }
}
