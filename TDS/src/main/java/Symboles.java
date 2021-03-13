package main.java;

public abstract class Symboles {

    protected String nom, type, cat;
    protected String nbParam, nbBloc;
    protected String rang, scope;
    protected Integer val;

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


    public Integer getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
