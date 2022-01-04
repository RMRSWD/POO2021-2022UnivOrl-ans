import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private LocalDate dateReservation;
    private int numDeTable;
    private int numPlaces;
    private double montant;

    public Reservation(int numDeTable, int numPlaces, double montant){
        dateReservation=LocalDate.now();
        this.numDeTable=numDeTable;
        this.numPlaces=numPlaces;
        this.montant=montant;
    }
    public String toString(){
        return "Numero de table reservée : "+numDeTable+"Nombre places reservées : "+ numPlaces+"Montant : "+montant+"Euro";
    }

    public int getNumDeTable() {
        return numDeTable;
    }

    public int getNumPlaces() {
        return numPlaces;
    }

}
