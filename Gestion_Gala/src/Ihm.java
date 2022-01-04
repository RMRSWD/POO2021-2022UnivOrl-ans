import java.util.Map;
import java.util.Scanner;

/** Cette classe gère toutes les interactions avec l'utilisateur.
 Elle se chargera d'afficher les informations, de récupérer les données saisies par l'utilisateur à l'aide de la classe Scanner
 et de les transmettre au contrôleur.
 */
public class Ihm {
    private Controleur controleur;


    /**
     * Constructeur de la Ihm qui prend en parametre un controleur
     * @param controleur est le controleur de la classe Ihm
     */

    public Ihm(Controleur controleur){
        this.controleur = controleur;
    }

    public int getEtuId(){
        System.out.println("entrer votre numero : ");
        Scanner sc = new Scanner (System.in);
        int c = sc.nextInt();
        return c;
    }
    public int getChoice(){
        System.out.println("entrer votre choice : ");
        Scanner sc = new Scanner (System.in);
        int c = sc.nextInt();
        return c;
    }

    public int getNbtablesEtu(){
        System.out.println("entrer votre table que vous voulez selectionner : ");
        Scanner sc = new Scanner (System.in);
        int c = sc.nextInt();
        return c;
    }

    public int getNbtablesPers(){

        do {
            System.out.println("entrer votre nombre personne: ");
            Scanner sc = new Scanner (System.in);
            int c = sc.nextInt();
            if (c==1 || c==2){

                return c;
            }
            System.out.println(" --------------->Erreur!<----------------");
            System.out.println("Vous pouvez seulement inscrire un maximum de deux personne. ");
            System.out.println("Veuillez entrer à nouveau!");
        }while(true);

    }

    public int printSubMenu(){
        System.out.println("Submenu:\n1. Gerer les places du diner\n2. Se desinscrire\n3. Quitter");
        return getChoice();
    }
    public int printMenu() {
        System.out.println("Menu of gala:\n1. Etudient \n2.  Personel\n3. Exit");
        return getChoice();
    }

    public void printErr() {
        System.out.println("Vous n'etes pas encore inscrire");
    }

    public void afficherSupprimerSucces() {
        System.out.println("Vous etes desinscrire avec succes");
    }

    public int getTableChoice() {
        System.out.println("Menu of gala:\n1. Sélection aléatoire \n2.  Réservation manuelle");
        return getChoice();
    }
}
