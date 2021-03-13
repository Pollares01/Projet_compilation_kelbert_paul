package main.java;

import java.lang.reflect.Field;

public class SymboleIdentifiant extends Symboles {


    public SymboleIdentifiant(String nom, String type, String cat, Integer val, String rang, String scope) {
        super(nom, type, cat);
        this.val = val;
        this.rang = rang;
        this.scope = scope;
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
        if(val!=null){
            res.append(", val=").append(val);
        }
        if(rang!=null){
            res.append(", rang=").append(rang);
        }
        if(scope!=null){
            res.append(", scope=").append(scope);
        }
        res.append("}");
        return res.toString();
    }
}
