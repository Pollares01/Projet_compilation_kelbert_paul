package main.java;

public abstract class Symboles {

    protected String nom, type, cat;

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
}
