import java.io.IOException;
import java.time.LocalDate;

/**
 * Cette classe contient la méthode main qui crée un objet de type Controleur en passant une date de gala en paramètre.
 */

public class Main {

    public static void main(String[] args) throws IOException, LignesIncorectesException, ClassNotFoundException {
        try {
            LocalDate d1 = LocalDate.of(2021, 12, 29);
            // Check file not exist
            Controleur c = new Controleur(d1, false);
            c.run();
            Controleur c2 = new Controleur(d1,true);
            c2.run();
        } catch (IOException ex) {
            System.out.println(ex.toString());
            System.out.println("Impossible de trouver le fichier gala.ser");
        }
    }


}
