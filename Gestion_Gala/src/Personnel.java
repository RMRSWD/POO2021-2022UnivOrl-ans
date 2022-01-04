import java.io.Serializable;
import java.util.Map;

public class Personnel extends Invites implements Serializable {

    /**
     * Constructeur du personnel qui utilise le contracteur de sa classe parent
     * @param numero est le numero du personnel
     * @param nom est le nom du personnel
     * @param prenom est le prenom du personnel
     * @param numTel est le numero de telephone du personnel
     * @param email est l email du personnel
     */
    public Personnel(int numero, String nom, String prenom, String numTel, String email){
        super(numero, nom, prenom, numTel, email);
        this.role = "Pers";
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "numero:" + getNumero() +"nom:"+getNom()+"prenom:"+getPrenom()+"nummero de telephone:"+getNumTel()+"Email"+getEmail()+ '\'' +
                '}';
    }
}
