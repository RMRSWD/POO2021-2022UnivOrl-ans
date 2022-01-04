import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe table qui contient toutes les informations de chaque table
 */
public class Table implements Serializable {
    private static int numeroSuivant = 1;// Aide pour l'auto incrementation des tables de reservation 1->25
    private int numero;
    private int nbPlacesLibres;// Il y a 8 places pour chaque table
    private Map<Integer,Integer>idParticipants;

    public Table(){
        this.numero=numeroSuivant++;
        nbPlacesLibres=8;
        idParticipants=new HashMap<>();
    }

    public Map<Integer, Integer> getIdParticipants() {
        return idParticipants;
    }

    /**
     * Methode qui retire des places a une table
     * @param place est le nombre de places a retirer
     */
    public void retirerPlaces(int place){
        this.nbPlacesLibres=this.nbPlacesLibres+place;
    }

    /**
     * Methode qui ajoute des places a une table
     * @param place le nombre de places a ajouter
     */
    public void ajouterPlaces(int place){
        this.nbPlacesLibres=this.nbPlacesLibres-place;
    }

    /**
     * Methode qui ajoute le nom des partipants a une table
     * @param numeroParticipant le numero du participant
     * @param idParticipant le id du participant
     */
    public void ajouterNomParticipant(int idParticipant, Integer numeroParticipant){
        this.idParticipants.put(idParticipant,numeroParticipant);

    }

    /**
     * Methode qui supprime le nom d un participant en fonction de son numero
     * @param numpers est le numero de la personne a supprimer
     * @throws NullPointerException verifier que numpersoCo est bien dans une table
     */
    public void supprimerNom(int numpers){
        if (idParticipants.containsKey(numpers)){
            this.retirerPlaces(idParticipants.get(numpers));
            idParticipants.remove(numpers);
        }

    }

    /**
     * Methode teste si une table est utilisable pour le nombre de places demandees
     * @param nombrePlace est le nombre de place a reserver sur cette table
     * @return true si le nombre de places libres de cette table est suffisant
     */
    public boolean tableDispo(int nombrePlace){
        return this.nbPlacesLibres>=nombrePlace;
    }
    public Map<Integer, Integer>getNomParticipants(){
        return idParticipants;
    }

    public int getNumero() {
        return numero;
    }
    public int getNbPlacesLibres(){
        return nbPlacesLibres;
    }

    public boolean checkReserver(int num) {
        return idParticipants.containsKey(num);
    }

}
