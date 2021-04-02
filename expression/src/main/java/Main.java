import fr.ul.miage.expression.ParserCup;
import generated.fr.ul.miage.expression.Yylex;
import fr.ul.miage.arbre.*;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Quel fichier voulez vous choisir ? : ");
            String filename = "samples/e" + sc.nextLine() + ".exp";
            try {
                Yylex scanner = new Yylex(new FileReader(filename));
                ParserCup pc = new ParserCup(scanner);
                pc.parse();
                TxtAfficheur.afficher(pc.resultat);
                System.out.println("OK\n");
            } catch (Exception e) {
                LOG.severe(e.getMessage());
                System.out.println("PAS OK");
                System.exit(1);
            }
            LOG.info("Terminé");
            System.out.println("\n\n");
        }


    }
}