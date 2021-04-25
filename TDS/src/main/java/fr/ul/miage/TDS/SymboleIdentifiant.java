package fr.ul.miage.TDS;

public class SymboleIdentifiant extends Symboles {


    public SymboleIdentifiant(String nom, String type, String cat, Integer val, Integer rang, SymboleFonction scope) {
        super(nom, type, cat);
        this.val = val;
        this.rang = rang;
        this.scope = scope;
    }

    @Override
    public String toTDS(){
        StringBuilder res = new StringBuilder("{");
        res.append("nom=")
                .append(nom)
                .append(", type=")
                .append(type)
                .append(", cat=")
                .append(cat);
        if(val!=null){
            res.append(", val=").append(val);
        }
        if(rang!=null){
            res.append(", rang=").append(rang);
        }
        if(scope!=null){
            res.append(", scope=").append(scope.getNom());
        }
        res.append("}");
        return res.toString();
    }

}
