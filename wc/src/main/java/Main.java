import generated.fr.ul.miage.wc.Yylex;

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
        String filename = sc.nextLine();
//        if(args.length != 1){
//            LOG.severe("Usage: commande nom-de-fichier");
//            System.exit(1);
//        } else {
//            filename = args[0];
//        }
        Reader reader;
        try {
            reader = new FileReader(filename);
            Yylex scanner = new Yylex(reader);
            scanner.yylex();
            System.out.println(scanner.getN());
        } catch (IOException e) {
            LOG.severe(e.getMessage());
            System.exit(1);
        }
        LOG.info("Terminé");
    }
}
