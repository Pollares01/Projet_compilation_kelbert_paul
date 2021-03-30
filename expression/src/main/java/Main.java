import fr.ul.miage.expression.ParserCup;
import generated.fr.ul.miage.expression.Yylex;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Quel fichier voulez vous choisir ? : ");
        String filename = "samples/" + sc.nextLine();
        Reader reader;
        try {
            ParserCup pc = new ParserCup(new Yylex(new FileReader(filename)));
            pc.parse();
            System.out.println("OK\n");
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            System.exit(1);
        }
        LOG.info("Terminé");
    }
}