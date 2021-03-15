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
}
