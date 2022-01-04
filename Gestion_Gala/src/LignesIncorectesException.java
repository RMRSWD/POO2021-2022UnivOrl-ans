import java.io.Serializable;


    public class LignesIncorectesException extends Exception implements Serializable {
        public LignesIncorectesException(String ligne_incorecte) {
            super(ligne_incorecte);
        }
    }

