import fr.ul.miage.TDS.TDS;
import fr.ul.miage.arbre.TxtAfficheur;
import generated.fr.ul.miage.grammaire.ParserCup;
import generated.fr.ul.miage.grammaire.Yylex;

import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            System.out.print("Quel fichier voulez vous choisir ? : ");
            String filename = "samples/ex" + sc.nextLine() + ".paul";
            try {
                Yylex scanner = new Yylex(new FileReader(filename));
                ParserCup pc = new ParserCup(scanner);
                pc.parse();
                TxtAfficheur.afficher(pc.resultat);
                TDS.afficherTDS(pc.tds);
                System.out.println("OK");
            } catch (Exception e) {
                LOG.severe(e.getMessage());
                System.out.println("PAS OK");
                System.exit(1);
            }
            LOG.info("Terminé");
            System.out.println("\n\n");


    }
}