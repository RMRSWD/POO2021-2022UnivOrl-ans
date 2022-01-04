import java.io.*;
import java.time.LocalDate;
/**
 * La classe controleur interagit avec le gala et la console et connait le numero de la personne connectee
 */
public class Controleur extends ServiceStockage {
    private Gala gala;
    private Ihm console;
    private LocalDate datecontroleur;

    private Invites user;


    /**
     * Constructeur de la classe controleur qui cree un fichier gala.ser si celui ci n existe pas
     *
     * @param d1 est la date du gala
     * @throws IOException               s il ny a pas assez de place pour creer le fichier gala.ser
     * @throws LignesIncorectesException si le fichier etudiantscsvet personnel.csv sont corompus
     * @throws ClassNotFoundException    si une classe nest pas trouvee lors de la serialization
     */


    public Controleur(LocalDate d1, boolean loadFile) throws IOException, LignesIncorectesException, ClassNotFoundException {
        super(d1);
        if (loadFile) {
            this.gala = (Gala) this.charger();
        } else {
            this.gala = new Gala(d1);
        }
        this.console = new Ihm(this);
        //this.gala = new Gala();
    }

    public void setGala(Gala gala) {
        this.gala = gala;
    }

    public Gala getGala() {
        return gala;
    }

    public void loginEtu() {
        int sid;
        boolean check;
        do {
            sid = console.getEtuId();
            check = gala.checkIdEtu(sid);
            if (!check) {
                System.out.println("Incorrect number(Example:2165002)");
            }
        }
        while (!check);
        System.out.println("Connecté avec succès (Etudiant)");
        this.user = gala.getEtu(sid);

    }

    public void loginPers() {
        int sid;
        boolean check;
        do {
            sid = console.getEtuId();
            // System.out.println(!gala.checkId(sid));
            check = gala.checkIdPer(sid);
            if (!check) {
                System.out.println("Incorrect number(Example:5114)");
            }
        }
        while (!check);
        System.out.println("Connecté avec succès (Personnel)");
        this.user = gala.getPers(sid);
    }

    public void loginEtuDirect(int sid) {
        this.user = gala.getEtu(sid);


    }

    public boolean reserver() {
        int tableId = console.getNbtablesEtu();
        int num = console.getNbtablesPers();
        return this.gala.reserver(tableId, num, user.getNumero(), user.getRole());
    }

    public void reserverDirect(int tableId, int num) {
        this.gala.reserver(tableId, num, user.getNumero(), user.getRole());
        return;
    }


    public void printTable() {
        this.gala.printTableLibres(this.user.getRole());
    }


    public void checkType() {

    }

    public void run() throws IOException {
        int choice;
        System.out.println("LA LISTE DES ETUDIANT");
        this.gala.printEtu();
        System.out.println("------------------------------------------------------------");
        System.out.println("LA LISTE DES PERSONNELS");
        this.gala.printPers();
        this.gala.printTable("Etu");
        System.out.println("------------------------------------------------------------");
        this.gala.printTable("Pers");
        do {
            choice = this.console.printMenu();
            if (choice == 3) {
                return;
            }
            if (choice == 1) {
                this.loginEtu();
                this.printTable();
                break;
            }
            if (choice == 2) {
                this.loginPers();
                this.printTable();
                break;
            }
        } while (true);

        boolean dejaInscrire = this.checkInscrire();
        if (dejaInscrire) {
            while (true) {
                int choice2 = this.console.printSubMenu();
                if (choice2 == 1) {
                    this.printTable();
                }
                if (choice2 == 2) {
                    gala.supprimer(this.user.getNumero(), this.user.getRole());
                    this.console.afficherSupprimerSucces();
                    break;
                }
                if (choice2 == 3) {
                    break;
                }
            }
            this.enregistrer(this.getGala());
            return;
        }
        int choice3 = console.getTableChoice();
        if (choice3 == 2) {
            this.printTable();
            while (true) {
                boolean isOk = this.reserver();
                if (!isOk) {
                    System.out.println("Le tableau n'est pas disponible. Resélectionner le tableau");
                    continue;
                }
                break;
            }
            System.out.println("succès");
        } else {
            int npers = console.getNbtablesPers();
            int tableId = this.reserverAleratoire(npers);
            if (tableId == -1) {
                System.out.println("échouer. aucune table n'est disponible.");
            } else {
                System.out.println("succès. Vous avez été inversé pour la table" + tableId);
            }
        }
        this.enregistrer(this.getGala());
        return;
    }

    private int reserverAleratoire(int npers) {//Selection
        int t = this.gala.reserverRandom(this.user.getNumero(), npers, this.user.getRole());

        return t;
    }

    private boolean checkInscrire() {
        return this.gala.checkReserver(this.user.getNumero(), this.user.getRole());
    }

}
