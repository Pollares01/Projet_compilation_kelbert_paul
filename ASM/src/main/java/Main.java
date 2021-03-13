import fr.ul.miage.arbre.*;
import main.java.SymboleFonction;
import main.java.SymboleIdentifiant;
import main.java.Symboles;
import main.java.TDS;

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


                    tds.afficherTDS();
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

                    tds2.afficherTDS();
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

                    main3.ajouterUnFils(aff3_1);
                    main3.ajouterUnFils(aff3_2);
                    prog3.ajouterUnFils(main3);
                    TxtAfficheur.afficher(prog3);

                    tds3.afficherTDS();

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

                    tds4.afficherTDS();
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

                    tds5.afficherTDS();
                    break;
                case "6":
                    System.out.println("-- EXEMPLE 6 --");

                    break;
                case "7":
                    System.out.println("-- EXEMPLE 7 --");

                    break;
                case "8":
                    System.out.println("-- EXEMPLE 8 --");

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
