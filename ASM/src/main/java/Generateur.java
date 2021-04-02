import fr.ul.miage.arbre.*;
import main.java.SymboleFonction;
import main.java.Symboles;
import main.java.TDS;


public class Generateur {

    /**
     * Genere une affectation en asm
     * @param a Affectation
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_affectation(Affectation a, TDS tds){
        StringBuilder res= new StringBuilder();
        res.append(generer_expression(a.getFils().get(1), tds));

        String fg = a.getFils().get(0).getLabel();
        String nom = fg.substring(fg.indexOf("/")+1);
        switch (tds.getIdfByName(nom).getCat()) {
            case "global":
                res.append("\tPOP(R0)\r\n" + "\tST(R0,").append(nom).append(")");
                break;
            case "local":
                res.append("\tPOP(R0)\r\n" + "\tPUTFRAME(R0,").append(tds.getIdfByName(nom).getRang() * 4).append(")");
                break;
            case "param":
                int nbParam = tds.getFonctionByName(String.valueOf(tds.getIdfByName(nom).getScope())).getNbParam();
                res.append("\tPOP(R0)\r\n" + "\tPUTFRAME(R0,").append((2 + nbParam - tds.getIdfByName(nom).getRang()) * -4).append(")");
                break;
        }
        return res.toString();
    }

    /**
     * Genere une expression en asm
     * @param n Noeud
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_expression(Noeud n, TDS tds){
        StringBuilder res = new StringBuilder();
        switch (n.getCat()){
            case CONST:
                res.append("\tCMOVE(").append(n.getLabel().replace("CONST/", "")).append(",R0)").append("\r\n");
                res.append("\tPUSH(R0)").append("\r\n");
                break;
            case IDF:
                String nomIdf = n.getLabel().replace("IDF/", "");
                switch (tds.getIdfByName(nomIdf).getCat()) {
                    case "global":
                        res.append("\tLD(").append(nomIdf).append(",R0)\r\n").append("\tPUSH(R0)\r\n");
                        break;
                    case "local":
                        res.append("\tGETFRAME(").append(tds.getIdfByName(nomIdf).getRang() * 4).append(",R0)\r\n").append("\tPUSH(R0)\r\n");
                        break;
                    case "param":
                        int nbParam = tds.getFonctionByName(String.valueOf(tds.getIdfByName(nomIdf).getScope())).getNbParam();
                        res.append("\tGETFRAME(").append((2 + nbParam - tds.getIdfByName(nomIdf).getRang()) * -4).append(",R0)\r\n").append("\tPUSH(R0)\r\n");
                        break;
                }
                break;
            case PLUS:
                Plus plus = (Plus) n;
                res.append(Generateur.generer_expression(plus.getFilsGauche(), tds));
                res.append(Generateur.generer_expression(plus.getFilsDroit(), tds));
                res.append("\tPOP(R2)").append("\r\n");
                res.append("\tPOP(R1)").append("\r\n");
                res.append("\tADD(R1,R2,R0)").append("\r\n");
                res.append("\tPUSH(R0)").append("\r\n");
                break;
            case MOINS:
                Moins moins = (Moins) n;
                res.append(Generateur.generer_expression(moins.getFilsGauche(), tds));
                res.append(Generateur.generer_expression(moins.getFilsDroit(), tds));
                res.append("\tPOP(R2)").append("\r\n");
                res.append("\tPOP(R1)").append("\r\n");
                res.append("\tSUB(R1,R2,R0)").append("\r\n");
                res.append("\tPUSH(R0)").append("\r\n");
                break;
            case MUL:
                Multiplication mul = (Multiplication) n;
                res.append(Generateur.generer_expression(mul.getFilsGauche(), tds));
                res.append(Generateur.generer_expression(mul.getFilsDroit(), tds));
                res.append("\tPOP(R2)").append("\r\n");
                res.append("\tPOP(R1)").append("\r\n");
                res.append("\tMUL(R1,R2,R0)").append("\r\n");
                res.append("\tPUSH(R0)").append("\r\n");
                break;
            case DIV:
                Division div = (Division) n;
                res.append(Generateur.generer_expression(div.getFilsGauche(), tds));
                res.append(Generateur.generer_expression(div.getFilsDroit(), tds));
                res.append("\tPOP(R2)").append("\r\n");
                res.append("\tPOP(R1)").append("\r\n");
                res.append("\tDIV(R1,R2,R0)").append("\r\n");
                res.append("\tPUSH(R0)").append("\r\n");
                break;
            case LIRE:
                res.append("\tRDINT()").append("\r\n");
                res.append("\tPUSH(R0)").append("\r\n");
                break;
            case APPEL:
                res.append(Generateur.generer_appel((Appel) n, tds));
                break;
        }
        return res.toString();
    }

    /**
     * Cree le debut d'un programme ASM
     * @return code ASM
     */
    public static String generer_debut_programme() {
        return ".include beta.uasm\r\n" +
                ".include intio.uasm\r\n" +
                ".options tty\n" +
                "\n" +
                "\tCMOVE(pile,SP)\r\n" +
                "\tBR(debut)\n";
    }

    /**
     * Cree la fin d'un programme ASM
     * @return code ASM
     */
    public static String generer_fin_programme() {
        return "debut:\r\n" +
                "\tCALL(main)\r\n" +
                "\tHALT()\r\n" +
                "pile:\n";
    }

    /**
     * Genere un code ASM
     * @param p Prog
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_programme(Prog p, TDS tds){
        StringBuilder res = new StringBuilder();
        res.append(generer_debut_programme());
        res.append(generer_data(tds));
        for(Noeud n : p.getFils()) {
            res.append(generer_fonction((Fonction) n, tds));
        }
        res.append(generer_fin_programme());
        return res.toString();
    }

    /**
     * Genere des datas en ASM
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_data(TDS tds){
        StringBuilder res = new StringBuilder();
        for (Symboles idf: tds.getIdentifiant()){
            if(idf.isGlobal()){
                if(idf.getVal() == null){
                    res.append(idf.getNom()).append(": \tLONG(").append(0).append(")\r\n");
                } else {
                    res.append(idf.getNom()).append(": \tLONG(").append(idf.getVal()).append(")\r\n");
                }
            }
        }
        return res.toString();
    }

    /**
     * Genere des instructions en ASM
     * @param n Noeud
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_instruction(Noeud n, TDS tds){
        StringBuilder res = new StringBuilder();
        switch (n.getCat()){
            case AFF:
                res.append(Generateur.generer_affectation((Affectation) n, tds)).append("\r\n");
                break;
            case ECR:
                res.append(Generateur.generer_ecrire((Ecrire) n, tds)).append("\r\n");
                break;
            case SI:
                res.append(Generateur.generer_si((Si) n, tds)).append("\r\n");
                break;
            case TQ:
                res.append(Generateur.generer_tq((TantQue) n, tds)).append("\r\n");
                break;
            case APPEL:
                res.append(Generateur.generer_appel((Appel) n, tds)).append("\r\n");
                break;
            case RET:
                res.append(Generateur.generer_retour((Retour) n, tds)).append("\r\n");
                break;
        }
        return res.toString();
    }

    /**
     * Genere un tant que en ASM
     * @param tq TantQue
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_tq(TantQue tq, TDS tds) {
        StringBuilder res = new StringBuilder();
        String num = tq.getLabel().replace("TQ/", "");
        res.append("tq").append(num).append(":").append("\r\n");
        res.append(Generateur.generer_condition(tq.getCondition(), tds));
        res.append("\tPOP(R0)").append("\r\n");
        res.append("\tBF(R0,ftq").append(num).append(")").append("\r\n");
        res.append(Generateur.generer_bloc(tq.getBloc(), tds));
        res.append("\tBR(tq").append(num).append(")").append("\r\n");
        res.append("ftq").append(num).append(":").append("\r\n");
        return res.toString();
    }

    /**
     * Genere la fonction Ecrire
     * @param n Ecrire
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_ecrire(Ecrire n, TDS tds){
        return Generateur.generer_expression(n.getLeFils(), tds) +
                "\tPOP(R0)" + "\r\n" +
                "\tWRINT()";
    }

    /**
     * Genere un Si en ASM
     * @param n Si
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_si(Si n, TDS tds){
        String num = n.getLabel().replace("SI/", "");
        return Generateur.generer_condition(n.getCondition(), tds)+
                "\tPOP(R0)" + "\r\n" +
                "\tBF(R0, sinon"+ num + ")" + "\r\n" +
                Generateur.generer_bloc(n.getBlocAlors(), tds)+
                "\tBR(fsi"+num+")" + "\r\n" +
                "sinon"+num+":" + "\r\n" +
                Generateur.generer_bloc(n.getBlocSinon(), tds) +
                "fsi"+num+":";
    }

    /**
     * Genere un bloc en ASM
     * @param b bloc
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_bloc(Bloc b, TDS tds){
        StringBuilder res = new StringBuilder();
        for (Noeud n : b.getFils()){
            res.append(Generateur.generer_instruction(n, tds));
        }
        return res.toString();
    }

    /**
     * Genere une condition en ASM
     * @param n Noeud
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_condition(Noeud n, TDS tds){
        StringBuilder res = new StringBuilder();
        switch (n.getCat()) {
            case CONST:
                res.append("\tCMOVE(").append(n.getLabel().replace("CONST/", "")).append(",R0)").append("\tPUSH(R0)\r\n");
                break;
            case INF:
                res.append(generer_expression(n.getFils().get(0), tds));
                res.append(generer_expression(n.getFils().get(1), tds));
                res.append("\tPOP(R2)\r\n" + "\tPOP(R1)\r\n" + "\tCMPLT(R1,R2,R0)\r\n" + "\tPUSH(R0)\r\n");
                break;
            case INFE:
                res.append(generer_expression(n.getFils().get(0), tds));
                res.append(generer_expression(n.getFils().get(1), tds));
                res.append("\tPOP(R2)\r\n" + "\tPOP(R1)\r\n" + "\tCMPLE(R1,R2,R0)\r\n" + "\tPUSH(R0)\r\n");
                break;
            case SUP:
                res.append(generer_expression(n.getFils().get(0), tds));
                res.append(generer_expression(n.getFils().get(1), tds));
                res.append("\tPOP(R2)\r\n" + "\tPOP(R1)\r\n" + "\tCMPLT(R2,R1,R0)\r\n" + "\tPUSH(R0)\r\n");
                break;
            case SUPE:
                res.append(generer_expression(n.getFils().get(0), tds));
                res.append(generer_expression(n.getFils().get(1), tds));
                res.append("\tPOP(R2)\r\n" + "\tPOP(R1)\r\n" + "\tCMPLE(R2,R1,R0)\r\n" + "\tPUSH(R0)\r\n");
                break;
            case EG:
                res.append(generer_expression(n.getFils().get(0), tds));
                res.append(generer_expression(n.getFils().get(1), tds));
                res.append("\tPOP(R2)\r\n" + "\tPOP(R1)\r\n" + "\tCMPEQ(R1,R2,R0)\r\n" + "\tPUSH(R0)\r\n");
                break;
            case DIF:
                res.append(generer_expression(n.getFils().get(0), tds));
                res.append(generer_expression(n.getFils().get(1), tds));
                res.append("\tPOP(R2)\r\n" + "\tPOP(R1)\r\n" + "\tCMPNEQ(R1,R2,R0)\r\n" + "\tPUSH(R0)\r\n");
                break;
        }
        return res.toString();
    }

    /**
     * Genere un appel en ASM
     * @param n Noeud
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_appel(Noeud n, TDS tds){
        StringBuilder res= new StringBuilder();
        SymboleFonction sF = tds.getFonctionByName(n.getLabel().replace("APPEL/", ""));
        if (!sF.getType().equalsIgnoreCase("void")) {
            res.append("\tALLOCATE(1)");
            for(Noeud nd : n.getFils()) {
                res.append(generer_expression(nd, tds));
            }
            res.append("\tCALL(").append(n.getLabel().replace("APPEL/", "")).append(")\r\n").append("\tDEALLOCATE(").append(sF.getNbParam()).append(")\r\n");
        }
        return res.toString();
    }

    /**
     * Genere une fonction en ASM
     * @param f Fonction
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_fonction(Fonction f, TDS tds){
        String nom = f.getLabel().replace("FONCTION/", "");
        StringBuilder res = new StringBuilder();
        SymboleFonction sF = tds.getFonctionByName(nom);
        res.append(nom).append(":\r\n");
        res.append("\tPUSH(LP)\r\n" + "\tPUSH(BP)\r\n" + "\tMOVE(SP,BP)\r\n" + "\tALLOCATE(").append(sF.getNbBloc()).append(")").append("\r\n");
        for (Noeud n : f.getFils()) {
            res.append(generer_instruction(n, tds));
        }
        res.append("return_").append(nom).append(":\r\n").append("\tDEALLOCATE(").append(sF.getNbBloc()).append(")\r\n").append("\tPOP(BP)\r\n").append("\tPOP(LP)\r\n").append("\tRTN()\r\n");
        return res.toString();
    }

    /**
     * Genere un retour en ASM
     * @param r Retour
     * @param tds TDS
     * @return code ASM
     */
    public static String generer_retour(Retour r, TDS tds){
        StringBuilder res= new StringBuilder();
        String nom = r.getLabel().replace("RET/", "");
        int nbParam = tds.getFonctionByName(nom).getNbParam();
        res.append(generer_expression(r.getLeFils(), tds));
        res.append("\tPOP(R0)\r\n" + "\tPUTFRAME(R0,").append((3+nbParam)*-4).append(")\r\n").append("\tBR(return_").append(nom).append(")\r\n");
        return res.toString();
    }

}
