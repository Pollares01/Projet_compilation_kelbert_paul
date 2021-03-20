import fr.ul.miage.arbre.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while(!stop){
            System.out.println("\n");
            System.out.print(
                "====================================\n" +
                        "          PROJET ASSEMBLEUR         \n" +
                        "====================================\n" +
                        "\t 1) Exemple 1\n" +
                        "\t 2) Exemple 2\n" +
                        "\t 3) Exemple 3\n" +
                        "\t 4) Exemple 4\n" +
                        "\t 5) Exemple 5\n" +
                        "\t 6) Exemple 6\n" +
                        "\t 7) Exemple 7\n" +
                        "\t 8) Exemple 8\n" +
                        "\t 9) Quitter\n" +
                        "\n Veuillez sélectionner un choix : "
            );
            String choix = scanner.nextLine();
            System.out.println("\n");
            switch (choix){
                case "1":
                    System.out.println("-- EXEMPLE 1 --");

                    ArrayList<Symboles> symboles = new ArrayList<>();
                    symboles.add(new SymboleFonction("main", "void", "fonction", null, null));
                    TDS tds = new TDS(symboles);

                    Noeud prog = new Prog();
                    prog.ajouterUnFils(new Fonction("main"));
                    TxtAfficheur.afficher(prog);

                    TDS.afficherTDS(tds);

                    System.out.println(Generateur.genererASM(prog, new StringBuilder()));

                    try {
                        PrintWriter pw = new PrintWriter("exemple1.asm", StandardCharsets.UTF_8);
                        pw.println(Generateur.genererASM(prog, new StringBuilder()));
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;
                case "2":
                    System.out.println("-- EXEMPLE 2 --");

                    ArrayList<Symboles> symboles2 = new ArrayList<>();
                    symboles2.add(new SymboleFonction("main", "void", "fonction", null, null));
                    symboles2.add(new SymboleIdentifiant("i", "int", "global", 10, null, null));
                    symboles2.add(new SymboleIdentifiant("j", "int", "global", 20, null, null));
                    symboles2.add(new SymboleIdentifiant("k", "int", "global", null, null, null));
                    symboles2.add(new SymboleIdentifiant("l", "int", "global", null, null, null));
                    TDS tds2 = new TDS(symboles2);

                    Noeud prog2 = new Prog();
                    prog2.ajouterUnFils(new Fonction("main"));
                    TxtAfficheur.afficher(prog2);

                    TDS.afficherTDS(tds2);
                    System.out.println(Generateur.genererASM(prog2, new StringBuilder()));

                    try {
                        PrintWriter pw = new PrintWriter("exemple2.asm", StandardCharsets.UTF_8);
                        pw.println(Generateur.genererASM(prog2, new StringBuilder()));
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    System.out.println("-- EXEMPLE 3 --");

                    ArrayList<Symboles> symboles3 = new ArrayList<>();
                    symboles3.add(new SymboleFonction("main", "void", "fonction", null, null));
                    symboles3.add(new SymboleIdentifiant("i", "int", "global", 10, null, null));
                    symboles3.add(new SymboleIdentifiant("j", "int", "global", 20, null, null));
                    symboles3.add(new SymboleIdentifiant("k", "int", "global", null, null, null));
                    symboles3.add(new SymboleIdentifiant("l", "int", "global", null, null, null));
                    TDS tds3 = new TDS(symboles3);

                    Noeud prog3 = new Prog();
                    Noeud main3 = new Fonction("main");

                    Affectation aff3_1 = new Affectation();
                    aff3_1.setFilsGauche(new Idf(tds3.getSymByName("k")));
                    aff3_1.setFilsDroit(new Const(2));


                    Multiplication multiplication3 = new Multiplication();
                    multiplication3.setFilsGauche(new Const(3));
                    multiplication3.setFilsDroit(new Idf(tds3.getSymByName("j")));
                    Plus plus3 = new Plus();
                    plus3.setFilsGauche(new Idf(tds3.getSymByName("i")));
                    plus3.setFilsDroit(multiplication3);
                    Affectation aff3_2 = new Affectation();
                    aff3_2.setFilsGauche(new Idf(tds3.getSymByName("l")));
                    aff3_2.setFilsDroit(plus3);

                    System.out.println(Generateur.generer_affectation(aff3_2, tds3));

                    main3.ajouterUnFils(aff3_1);
                    main3.ajouterUnFils(aff3_2);
                    prog3.ajouterUnFils(main3);
                    TxtAfficheur.afficher(prog3);

                    TDS.afficherTDS(tds3);


                    System.out.println(Generateur.genererASM(prog3, new StringBuilder()));
                    try {
                        PrintWriter pw = new PrintWriter("exemple3.asm", StandardCharsets.UTF_8);
                        pw.println(Generateur.genererASM(prog3, new StringBuilder()));
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    System.out.println("-- EXEMPLE 4 --");

                    ArrayList<Symboles> symboles4 = new ArrayList<>();
                    symboles4.add(new SymboleFonction("main", "void", "fonction", null, null));
                    symboles4.add(new SymboleIdentifiant("i", "int", "global", null, null, null));
                    symboles4.add(new SymboleIdentifiant("j", "int", "global", 20, null, null));
                    TDS tds4 = new TDS(symboles4);

                    Noeud prog4 = new Prog();
                    Noeud main4 = new Fonction("main");

                    Affectation aff4 = new Affectation();
                    aff4.setFilsGauche(new Idf(tds4.getSymByName("i")));
                    aff4.setFilsDroit(new Lire());

                    Plus plus4 = new Plus();
                    plus4.setFilsGauche(new Idf(tds4.getSymByName("i")));
                    plus4.setFilsDroit(new Idf(tds4.getSymByName("j")));
                    Ecrire ecrire4 = new Ecrire();
                    ecrire4.ajouterUnFils(plus4);

                    main4.ajouterUnFils(aff4);
                    main4.ajouterUnFils(ecrire4);
                    prog4.ajouterUnFils(main4);
                    TxtAfficheur.afficher(prog4);

                    TDS.afficherTDS(tds4);
                    System.out.println(Generateur.genererASM(prog4, new StringBuilder()));
                    try {
                        PrintWriter pw = new PrintWriter("exemple4.asm", StandardCharsets.UTF_8);
                        pw.println(Generateur.genererASM(prog4, new StringBuilder()));
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    System.out.println("-- EXEMPLE 5 --");

                    ArrayList<Symboles> symboles5 = new ArrayList<>();
                    symboles5.add(new SymboleFonction("main", "void", "fonction", null, null));
                    symboles5.add(new SymboleIdentifiant("i", "int", "global", null, null, null));
                    TDS tds5 = new TDS(symboles5);

                    Noeud prog5 = new Prog();
                    Noeud main5 = new Fonction("main");

                    Affectation aff5 = new Affectation();
                    aff5.setFilsGauche(new Idf(tds5.getSymByName("i")));
                    aff5.setFilsDroit(new Lire());

                    Superieur sup5 = new Superieur();
                    sup5.setFilsGauche(new Idf(tds5.getSymByName("i")));
                    sup5.setFilsDroit(new Const(10));

                    Ecrire ecrire_bloc5_si1 = new Ecrire();
                    ecrire_bloc5_si1.ajouterUnFils(new Const(1));
                    Bloc bloc5_si1 = new Bloc();
                    bloc5_si1.ajouterUnFils(ecrire_bloc5_si1);

                    Ecrire ecrire_bloc5_si2 = new Ecrire();
                    ecrire_bloc5_si2.ajouterUnFils(new Const(2));
                    Bloc bloc5_si2 = new Bloc();
                    bloc5_si2.ajouterUnFils(ecrire_bloc5_si2);

                    Si si5 = new Si(1);
                    si5.setCondition(sup5);
                    si5.setBlocAlors(bloc5_si1);
                    si5.setBlocSinon(bloc5_si2);

                    main5.ajouterUnFils(aff5);
                    main5.ajouterUnFils(si5);
                    prog5.ajouterUnFils(main5);
                    TxtAfficheur.afficher(prog5);

                    TDS.afficherTDS(tds5);
                    System.out.println(Generateur.genererASM(prog5, new StringBuilder()));
                    try {
                        PrintWriter pw = new PrintWriter("exemple5.asm", StandardCharsets.UTF_8);
                        pw.println(Generateur.genererASM(prog5, new StringBuilder()));
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "6":
                    System.out.println("-- EXEMPLE 6 --");

                    ArrayList<Symboles> symboles6 = new ArrayList<>();
                    symboles6.add(new SymboleFonction("main", "void", "fonction", null, null));
                    symboles6.add(new SymboleIdentifiant("i", "int", "global", null, null, null));
                    symboles6.add(new SymboleIdentifiant("n", "int", "global", 5, null, null));
                    TDS tds6 = new TDS(symboles6);

                    Noeud prog6 = new Prog();
                    Noeud main6 = new Fonction("main");

                    Affectation aff6 = new Affectation();
                    aff6.setFilsGauche(new Idf(tds6.getSymByName("i")));
                    aff6.setFilsDroit(new Const(0));

                    Idf i6 = new Idf(tds6.getSymByName("i"));
                    Superieur sup6 = new Superieur();
                    sup6.setFilsGauche(i6);
                    sup6.setFilsDroit(i6);
                    Bloc bloc6_tq = new Bloc();
                    Ecrire ecrire6 = new Ecrire();
                    Plus plus6 = new Plus();
                    plus6.setFilsGauche(i6);
                    plus6.setFilsDroit(new Const(1));
                    Affectation Affectation6 = new Affectation();
                    Affectation6.setFilsGauche(i6);
                    Affectation6.setFilsDroit(plus6);
                    ecrire6.ajouterUnFils(i6);
                    bloc6_tq.ajouterUnFils(ecrire6);
                    bloc6_tq.ajouterUnFils(Affectation6);
                    TantQue tq6 = new TantQue(1);
                    tq6.setCondition(sup6);
                    tq6.setBloc(bloc6_tq);

                    main6.ajouterUnFils(aff6);
                    main6.ajouterUnFils(tq6);
                    prog6.ajouterUnFils(main6);
                    TxtAfficheur.afficher(prog6);

                    TDS.afficherTDS(tds6);
                    System.out.println(Generateur.genererASM(prog6, new StringBuilder()));
                    try {
                        PrintWriter pw = new PrintWriter("exemple6.asm", StandardCharsets.UTF_8);
                        pw.println(Generateur.genererASM(prog6, new StringBuilder()));
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "7":
                    System.out.println("-- EXEMPLE 7 --");

                    ArrayList<Symboles> symboles7 = new ArrayList<>();
                    symboles7.add(new SymboleFonction("main", "void", "fonction", null, null));
                    symboles7.add(new SymboleIdentifiant("a", "int", "global", 10, null, null));
                    SymboleFonction sF7 = new SymboleFonction("f", "void", "fonction", 1, 2);
                    symboles7.add(sF7);
                    symboles7.add(new SymboleIdentifiant("i", "int", "param", null, 0, sF7));
                    symboles7.add(new SymboleIdentifiant("x", "int", "local", null, 0, sF7));
                    symboles7.add(new SymboleIdentifiant("y", "int", "local", null, 1, sF7));
                    TDS tds7 = new TDS(symboles7);

                    Noeud prog7 = new Prog();
                    Noeud main7 = new Fonction("main");
                    Noeud f7 = new Fonction("f");

                    Idf i7 = new Idf(tds7.getSymByName("i"));
                    Idf y7 = new Idf(tds7.getSymByName("y"));
                    Idf x7 = new Idf(tds7.getSymByName("x"));
                    Idf a7 = new Idf(tds7.getSymByName("a"));

                    Affectation Affectation7_1 = new Affectation();
                    Affectation7_1.setFilsGauche(i7);
                    Affectation7_1.setFilsDroit(new Const(0));
                    Affectation Affectation7_2 = new Affectation();
                    Affectation7_2.setFilsGauche(y7);
                    Affectation7_2.setFilsDroit(new Const(1));

                    Plus plus7_1 = new Plus();
                    plus7_1.setFilsGauche(x7);
                    plus7_1.setFilsDroit(y7);
                    Plus plus7_2 = new Plus();
                    plus7_2.setFilsGauche(i7);
                    plus7_2.setFilsDroit(plus7_1);
                    Affectation Affectation7_3 = new Affectation();
                    Affectation7_3.setFilsGauche(a7);
                    Affectation7_3.setFilsDroit(plus7_2);

                    f7.ajouterUnFils(Affectation7_1);
                    f7.ajouterUnFils(Affectation7_2);
                    f7.ajouterUnFils(Affectation7_3);

                    Appel appel7 = new Appel("f");
                    appel7.ajouterUnFils(new Const(3));
                    Ecrire ecrire7 = new Ecrire();
                    ecrire7.ajouterUnFils(a7);
                    main7.ajouterUnFils(appel7);
                    main7.ajouterUnFils(ecrire7);

                    prog7.ajouterUnFils(f7);
                    prog7.ajouterUnFils(main7);
                    TxtAfficheur.afficher(prog7);

                    TDS.afficherTDS(tds7);
                    System.out.println(Generateur.genererASM(prog7, new StringBuilder()));
                    try {
                        PrintWriter pw = new PrintWriter("exemple7.asm", StandardCharsets.UTF_8);
                        pw.println(Generateur.genererASM(prog7, new StringBuilder()));
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "8":
                    System.out.println("-- EXEMPLE 8 --");

                    ArrayList<Symboles> symboles8 = new ArrayList<>();
                    symboles8.add(new SymboleFonction("main", "void", "fonction", null, null));
                    symboles8.add(new SymboleIdentifiant("a", "int", "global", null, null, null));
                    SymboleFonction sF8 = new SymboleFonction("f", "int", "fonction", 2, 1);
                    symboles8.add(sF8);
                    symboles8.add(new SymboleIdentifiant("x", "int", "local", null, 0, sF8));
                    symboles8.add(new SymboleIdentifiant("i", "int", "param", null, 0, sF8));
                    symboles8.add(new SymboleIdentifiant("j", "int", "param", null, 1, sF8));
                    TDS tds8 = new TDS(symboles8);

                    Noeud prog8 = new Prog();
                    Noeud main8 = new Fonction("main");
                    Noeud f8 = new Fonction("f");

                    Idf i8 = new Idf(tds8.getSymByName("i"));
                    Idf j8 = new Idf(tds8.getSymByName("j"));
                    Idf x8 = new Idf(tds8.getSymByName("x"));
                    Idf a8 = new Idf(tds8.getSymByName("a"));

                    Plus plus8_1 = new Plus();
                    plus8_1.setFilsGauche(i8);
                    plus8_1.setFilsDroit(j8);
                    Affectation Affectation8_1 = new Affectation();
                    Affectation8_1.setFilsGauche(x8);
                    Affectation8_1.setFilsDroit(plus8_1);

                    Plus plus8_2 = new Plus();
                    plus8_2.setFilsGauche(x8);
                    plus8_2.setFilsDroit(new Const(10));
                    Retour retour8 = new Retour("f");
                    retour8.setLeFils(plus8_2);

                    f8.ajouterUnFils(Affectation8_1);
                    f8.ajouterUnFils(retour8);

                    Appel appel8 = new Appel("f");
                    appel8.ajouterUnFils(new Const(1));
                    appel8.ajouterUnFils(new Const(2));
                    Affectation Affectation8_2 = new Affectation();
                    Affectation8_2.setFilsGauche(a8);
                    Affectation8_2.setFilsDroit(appel8);
                    Ecrire ecrire8 = new Ecrire();
                    ecrire8.ajouterUnFils(a8);

                    main8.ajouterUnFils(Affectation8_2);
                    main8.ajouterUnFils(ecrire8);

                    prog8.ajouterUnFils(f8);
                    prog8.ajouterUnFils(main8);
                    TxtAfficheur.afficher(prog8);

                    TDS.afficherTDS(tds8);
                    System.out.println(Generateur.genererASM(prog8, new StringBuilder()));


                    try {
                        PrintWriter pw = new PrintWriter("exemple8.asm", StandardCharsets.UTF_8);
                        pw.println(Generateur.genererASM(prog8, new StringBuilder()));
                        pw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    break;
                case "9":
                    stop = true;
                    break;
                default:
                    System.out.println("Veuillez sélectionner un choix de la liste.");
            }
        }
    }

}
