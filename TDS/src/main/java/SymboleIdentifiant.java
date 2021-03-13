package main.java;

public class SymboleIdentifiant extends Symboles {

    private String rang, scope;

    public SymboleIdentifiant(String nom, String type, String cat, String rang, String scope) {
        super(nom, type, cat);
        this.rang = rang;
        this.scope = scope;
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

    @Override
    public String toString() {
        return "{" +
                "nom=" + nom +
                ", type=" + type +
                ", cat=" + cat +
                ", rang=" + rang +
                ", scope='" + scope +
                '}';
    }
}
