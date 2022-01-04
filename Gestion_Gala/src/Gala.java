import java.io.*;
import java.time.LocalDate;
import java.util.*;
/**
 * La classe gala est la classe qui gere tout le gala avec toutes les collections pour la gerer
 */
public class Gala implements Serializable {
    private PriorityQueue reserList;
    private final Comparator<Etudiant> comparator = new SortbyAnne();
    private PriorityQueue<Etudiant> listEtu = new PriorityQueue<Etudiant>(100, comparator);
    private String test = "Init";
//    private PriorityQueue<Personnel> listPers = new PriorityQueue<Personnel>();

    private List<Personnel> listPers = new ArrayList();  //

    private LocalDate dateReser;

    private Table[] tablesEtu; // Toutes les tables des etudiants (Cle numetoTabe)
    private Table[] tablesPerson; // Tous les tables du personnel (Cle numeroTable)

    private final double tarif1 = 10.0;
    private final double tarif2 = 15.0;
    private final double tarif3 = 20.0;

    private final int nbtablesEtu = 15;
    private final int nbtablesPers = 10;
    static final long serialVersionUID = 1L;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Gala(LocalDate dateReser) {
        this.dateReser = dateReser;

        this.ReadFile("etudiants.txt");
        this.ReadFile("personnel.txt");

        this.tablesEtu = new Table[nbtablesEtu + 1];
        this.tablesPerson = new Table[nbtablesPers + 1];
        for (int i = 1; i <= nbtablesEtu; i++) {
            this.tablesEtu[i] = new Table();
        }
        for (int j = 1; j <= nbtablesPers; j++) {
            this.tablesPerson[j] = new Table();
        }
//
    }


    public void ReadFile(String filename) {
        String ligne;

        if (filename.equals("etudiants.txt")) { //
            Scanner scEtu = new Scanner(filename);

            while (scEtu.hasNextLine()) {
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader("etudiants.txt"));
                    ligne = scEtu.nextLine();
                    while (ligne != null) {
                        ligne = reader.readLine();
                        if (ligne == null) {
                            break;
                        }
                        String[] split = ligne.split("\\s+"); //

                        //System.out.println(split[0]);
                        int id = Integer.parseInt(split[0]);
                        int anneF = Integer.parseInt(split[5]);
                        Etudiant e = new Etudiant(id, split[1], split[2], split[3], split[4], anneF);

                        this.listEtu.add(e);
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            scEtu.close();
        } else {
            Scanner scPers = new Scanner(filename);
            while (scPers.hasNextLine()) {
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader("personnel.txt"));
                    ligne = scPers.nextLine();
                    while (ligne != null) {
                        ligne = reader.readLine();
                        if (ligne == null) {
                            break;
                        }
                        String[] split = ligne.split("\\s+");
                        int id = Integer.parseInt(split[0]);
                        Personnel p = new Personnel(id, split[1], split[2], split[3], split[4]);

                        this.listPers.add(p);
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();


                }

            }
            scPers.close();
        }
    }


    public void printEtu() {
        if (this.listEtu == null) {
            return;
        }
        for (Etudiant e : this.listEtu) {
            System.out.println(e);
        }
    }
    public void printPers(){
        if (this.listPers == null) {
            return;
        }
        for (Personnel p : this.listPers) {
            System.out.println(p);
        }
    }

    public boolean checkIdEtu(int sid) {
        for (Etudiant e : this.listEtu) {
            if (e.getNumero() == sid) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIdPer(int sid) {
        for (Personnel p : this.listPers) {
            if (p.getNumero() == sid) {
                return true;
            }
        }
        return false;
    }

    public Invites getEtu(int sid) {
        for (Etudiant e : this.listEtu) {
            if (e.getNumero() == sid) {
                return e;
            }
        }
        return null;


    }

    public boolean checkTable(int tableId, int num, String role) {
        if (role.equals("Etu")) {
            if (tableId >= nbtablesEtu || tableId < 1) {
                return false;
            }
            return this.tablesEtu[tableId].tableDispo(num);
        }
        if (tableId >= nbtablesPers || tableId < 1) {
            return false;
        }
        return this.tablesPerson[tableId].tableDispo(num);
    }

    public boolean checkReserver(int num, String role) {
        if (role.equals("Etu")) {
            for (int i = 1; i <= nbtablesEtu; i++) {
                if (this.tablesEtu[i].checkReserver(num)) {
                    return true;
                }
            }
        }
        if (role.equals("Pers")) {
            for (int i = 1; i <= nbtablesPers; i++) {
                if (this.tablesPerson[i].checkReserver(num)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean reserver(int tableId, int num, int id, String role) {
        if (role.equals("Etu")) {
            if (checkTable(tableId, num, role)) {
                tablesEtu[tableId].ajouterPlaces(num);
                tablesEtu[tableId].ajouterNomParticipant(id, num);
                return true;
            }
        }
        if (role.equals("Pers")) {
            if (checkTable(tableId, num, role)) {
                tablesPerson[tableId].ajouterPlaces(num);
                tablesPerson[tableId].ajouterNomParticipant(id, num);
                return true;
            }
        }
        return false;
    }

    public void printTable(String role) {
        if (role.equals("Etu")) {
            for (int i = 1; i <= this.nbtablesEtu; i++) {
                System.out.println("Table Etudiant: " + i);
                System.out.println("nombre places sont libres: " + tablesEtu[i].getNbPlacesLibres());
                System.out.println("Les personnes sont deja inscrit au cette table: ");
                for (Map.Entry<Integer, Integer> entry : tablesEtu[i].getIdParticipants().entrySet()) {
                    System.out.println(entry.getKey() + "-" + entry.getValue());
                }
            }
            return;
        }
        for (int i = 1; i <= this.nbtablesPers; i++) {
            System.out.println("Table Personnel: " + i);
            System.out.println("nombre places sont libres: " + tablesPerson[i].getNbPlacesLibres());
            System.out.println("Les personnes sont deja inscrit au cette table: ");
            for (Map.Entry<Integer, Integer> entry : tablesPerson[i].getIdParticipants().entrySet()) {
                System.out.println(entry.getKey() + "-" + entry.getValue());
            }
        }
    }

    public void printTableLibres(String role) {
        System.out.println("++ " + role + " " + this.nbtablesEtu);
        if (role.equals("Etu")) {
            for (int i = 1; i <= this.nbtablesEtu; i++) {
                System.out.println("Table " + i + ":nombre places sont libres: " + tablesEtu[i].getNbPlacesLibres());
            }
            return;
        }
        for (int i = 1; i <= this.nbtablesPers; i++) {
            System.out.println("Table " + i + ":nombre places sont libres: " + tablesPerson[i].getNbPlacesLibres());

        }
    }

    public void supprimer(int id, String role) {
        if (role.equals("Etu")) {
            for (int i = 1; i <= nbtablesEtu; i++) {
                tablesEtu[i].supprimerNom(id);
            }
        }
        if (role.equals("Pers")) {
            for (int i = 1; i <= nbtablesPers; i++) {
                tablesPerson[i].supprimerNom(id);
            }
        }
    }

    public Invites getPers(int sid) {
        for (Personnel e : this.listPers) {
            if (e.getNumero() == sid) {
                return e;
            }
        }
        return null;
    }


    public void remove(int num, String role) {
        if (role.equals("Etu")) {
            for (int i = 1; i <= nbtablesEtu; i++) {
                if (this.tablesEtu[i].checkReserver(num)) {
                    this.tablesEtu[i].supprimerNom(num);
                }
            }
        }
        if (role.equals("Pers")) {
            for (int i = 1; i <= nbtablesPers; i++) {
                if (this.tablesPerson[i].checkReserver(num)) {
                    this.tablesEtu[i].supprimerNom(num);
                }
            }
        }
    }

    public int reserverRandom(int numero, int num, String role) {
        if (role.equals("Etu")) {
            for (int tableId = 1; tableId < nbtablesEtu; tableId++) {
                if (checkTable(tableId, num, role)) {
                    tablesEtu[tableId].ajouterPlaces(num);
                    tablesEtu[tableId].ajouterNomParticipant(numero, num);
                    return tableId;
                }
            }
        }
        if (role.equals("Pers")) {
            for (int tableId = 1; tableId < nbtablesPers; tableId++) {
                if (checkTable(tableId, num, role)) {
                    tablesPerson[tableId].ajouterPlaces(num);
                    tablesPerson[tableId].ajouterNomParticipant(numero, num);
                    return tableId;
                }
            }
        }
        return -1;
    }
}


class SortbyAnne implements Comparator<Etudiant>, Serializable {
    // Method
    // Sorting in ascending order of roll number
    public int compare(Etudiant a, Etudiant b) {

        return a.getAnneeFormation() - b.getAnneeFormation();
    }


}

