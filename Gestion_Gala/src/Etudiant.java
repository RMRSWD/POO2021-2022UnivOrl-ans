import java.io.Serializable;

/**
 * MEthode qui herite de la classe invite avec une annee de formation en plus
 */
public class Etudiant extends Invites implements Serializable {
    private int anneeFormation; //de 1 à 5


    public Etudiant(int numero, String nom, String prenom, String numTel, String email, int anneeFormation) {
        super(numero, nom, prenom, numTel, email);
        this.anneeFormation = anneeFormation;
        this.role = "Etu";

    }

    public int getAnneeFormation() {
        return this.anneeFormation;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "numero:" + getNumero() +"nom:"+getNom()+"prenom:"+getPrenom()+"nummero de telephone:"+getNumTel()+"Email"+getEmail()+ "annee de formation:"+getAnneeFormation()+
                '}';
    }

}