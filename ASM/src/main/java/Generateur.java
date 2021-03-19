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
            res.append(Generateur.generer_fonction(n, tds)).append("\r\n");
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

    public static String generer_instruction(Noeud n, TDS tds){
        StringBuilder res = new StringBuilder();
        switch (n.getCat()){
            case AFF:
                res.append(Generateur.generer_affectation((Affectation) n)).append("\r\n");
                break;
            case ECR:
                res.append(Generateur.generer_ecrire((Ecrire) n)).append("\r\n");
                break;
            case SI:
                res.append(Generateur.generer_si((Si) n, tds)).append("\r\n");
                break;
            case TQ:
                res.append(Generateur.generer_tq((TantQue) n)).append("\r\n");
                break;
            case APPEL:
                res.append(Generateur.generer_appel((Fonction) n, tds)).append("\r\n");
                break;
            case RET:
                res.append(Generateur.generer_retour((Retour) n, tds)).append("\r\n");
                break;
        }
        return res.toString();
    }

    public static String generer_tq(TantQue tq) {
        return null;
    }

    public static String generer_ecrire(Ecrire n){
        return Generateur.generer_expression(n.getLeFils()) +
                "POP(R0)" + "\r\n" +
                "WRINT()" + "\r\n";
    }

    public static String generer_si(Si n, TDS tds){
        return Generateur.generer_condition(n.getCondition()) + "\r\n" +
                "POP(R0)" + "\r\n" +
                "BF(R0, Sinon 2)" + "\r\n" +
                Generateur.generer_bloc(n.getBlocAlors(), tds) + "\r\n" +
                "BR(fsi 3)" + "\r\n" +
                "Sinon 2:" + "\r\n" +
                Generateur.generer_bloc(n.getBlocAlors(), tds) + "\r\n" +
                "fsi 3:" + "\r\n";
    }

    public static String generer_bloc(Bloc b, TDS tds){
        StringBuilder res = new StringBuilder();
        for (Noeud n : b.getFils()){
            res.append(Generateur.generer_instruction(n, tds));
        }
        return res.toString();
    }

    //TODO
    public static String generer_condition(Noeud n){
        return null;
    }

    public static String generer_appel(Fonction a, TDS tds){
        StringBuilder res = new StringBuilder();
        SymboleFonction sf = tds.getFonctionByName(a.getLabel());
        if(!sf.getType().equalsIgnoreCase("void")){
            res.append("ALLOCATE(1)").append("\r\n");
            for (Noeud n : a.getFils()){
                res.append(Generateur.generer_expression(n));
            }
            res.append("CALL(").append(a.getLabel()).append(")").append("\r\n");
            res.append("DEALLOCATE(").append(sf.getNbParam()).append(")").append("\r\n");
        }
        return res.toString();
    }

    //TODO
    public static String generer_fonction(Fonction a, TDS tds){
        SymboleFonction sf = tds.getFonctionByName(a.getLabel());
        StringBuilder res = new StringBuilder();
        res.append("PUSH(LP)").append("\r\n");
        res.append("PUSH(BP)").append("\r\n");
        res.append("MOVE(SP, BP)").append("\r\n");
        res.append("ALLOCATE(").append(sf.getNbBloc()).append(")").append("\r\n");
        for(Noeud n : a.getFils()){
            res.append(Generateur.generer_instruction(n, tds));
        }
        res.append("return_").append(a.getLabel()).append("):").append("\r\n");
        res.append("\tDEALLOCATE(").append(sf.getNbBloc()).append(")").append("\r\n");
        res.append("\tPOP(BP)").append("\r\n");
        res.append("\tPOP(LP)").append("\r\n");
        res.append("\tRTN(L)").append("\r\n");
        return res.toString();
    }

    //TODO
    public static String generer_retour(Retour r, TDS tds){
        StringBuilder res = new StringBuilder();
        SymboleFonction sf = tds.getFonctionByName(r.getLeFils().getLabel());
        res.append(Generateur.generer_expression(r.getLeFils()));
        int offset = 1 + sf.getNbParam();
        res.append("POP(R0)").append("\r\n");
        res.append("PUTFRAME(R0,").append(-4*offset).append(")").append("\r\n");
        res.append("BR(return_").append(sf.getNom()).append(")").append("\r\n");
        return res.toString();
    }
}
