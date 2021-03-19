public class SymboleFonction extends Symboles {

    public SymboleFonction(String nom, String type, String cat, Integer nbParam, Integer nbBloc) {
        super(nom, type, cat);
        this.nbParam = nbParam;
        this.nbBloc = nbBloc;
    }

    @Override
    public String toTDS() {
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
