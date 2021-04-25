package fr.ul.miage.TDS;

public abstract class Symboles {

    protected String nom, type, cat;
    protected Integer nbParam, nbBloc;
    protected SymboleFonction scope;
    protected Integer rang, val;

    public Symboles(String nom, String type, String cat) {
        this.nom = nom;
        this.type = type;
        this.cat = cat;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public Integer getNbParam() {
        return nbParam;
    }

    public void setNbParam(Integer nbParam) {
        this.nbParam = nbParam;
    }

    public Integer getNbBloc() {
        if(this.nbBloc == null){
            return 0;
        }
        return this.nbBloc;
    }

    public void setNbBloc(Integer nbBloc) {
        this.nbBloc = nbBloc;
    }

    public SymboleFonction getScope() {
        return scope;
    }

    public void setScope(SymboleFonction scope) {
        this.scope = scope;
    }

    public Integer getRang() {
        return rang;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public boolean isGlobal(){
        return this.cat.equalsIgnoreCase("global");
    }

    public abstract String toTDS();

    @Override
    public String toString() {
        return this.getNom();
    }
}
