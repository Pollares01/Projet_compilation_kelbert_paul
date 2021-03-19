import fr.ul.miage.arbre.*;


public class Generateur {

    public static String genererASM(Noeud arbre, StringBuilder ASM){
//        System.out.println("CATEGORIE : " + arbre.getCat());
        switch (arbre.getCat()){
            case PROG:
                ASM.append("prog ");
                for(Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case FONCTION:
                ASM.append("fonction ");
                for(Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case BLOC:
                ASM.append("bloc ");
                for(Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case AFF:
                ASM.append("aff ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case SI:
                ASM.append("si ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case TQ:
                ASM.append("tq ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case ECR:
                ASM.append("ecr ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case RET:
                ASM.append("ret ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case PLUS:
                ASM.append("plus ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case MOINS:
                ASM.append("moins ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case DIV:
                ASM.append("div ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case MUL:
                ASM.append("mul ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case SUP:
                ASM.append("sup ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case INF:
                ASM.append("inf ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case SUPE:
                ASM.append("supe ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case INFE:
                ASM.append("infe ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case EG:
                ASM.append("eg ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case DIF:
                ASM.append("dif ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            case IDF:
                ASM.append("idf ");
                break;
            case CONST:
                ASM.append("const ");
                break;
            case LIRE:
                ASM.append("lire ");
                break;
            case APPEL:
                ASM.append("appel ");
                for (Noeud n : arbre.getFils()){
                    genererASM(n, ASM);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + arbre.getCat());
        }
        return ASM.toString();
    }

    //TODO
    public static String generer_affectation(Affectation a){
        return Generateur.generer_expression(a.getFilsDroit()) + "\r\n" +
                "POP(R0)\r\n" + "ST(RO," + a.getFilsGauche().getLabel().replace("IDF/", "") + ")\r\n";
    }

    //TODO
    public static String generer_expression(Noeud n){
        return null;
    }


    public static String generer_programme(Prog p, TDS tds){
        StringBuilder res = new StringBuilder();
        res.append(".include beta.uasm" + "\r\n")
        .append(".options tty" + "\r\n")
        .append(".include intio.uasm" + "\r\n");
        res.append(Generateur.generer_data(tds));
        for (Noeud n : p.getFils()) {
            res.append(Generateur.generer_fonction(n)).append("\r\n");
        }
        res.append("debut:\r\n" +
                "\tCALL(main)\r\n" +
                "\tHALT()\r\n" +
                "pile:\r\n");
        return res.toString();
    }

    public static String generer_data(TDS tds){
        StringBuilder res = new StringBuilder();
        for (Symboles idf: tds.getIdentifiant()){
            if(idf.isGlobal()){
                if(idf.getVal() == null){
                    res.append(idf.getNom()).append(": LONG(").append(0).append(")\r\n");
                } else {
                    res.append(idf.getNom()).append(": LONG(").append(idf.val).append(")\r\n");
                }
            }
        }
        return res.toString();
    }

    //TODO
    public static String generer_instruction(Noeud n){
        return null;
    }

    //TODO
    public static String generer_ecrire(Noeud n){
        return null;
    }

    //TODO
    public static String generer_si(Noeud n){
        return null;
    }

    //TODO
    public static String generer_bloc(Noeud n){
        return null;
    }

    //TODO
    public static String generer_condition(Noeud n){
        return null;
    }

    //TODO
    public static String generer_appel(Noeud n){
        return null;
    }

    //TODO
    public static String generer_fonction(Noeud n){
        return null;
    }

    //TODO
    public static String generer_retour(Noeud n){
        return null;
    }
}
