import java.io.Serializable;

/*
Class abstraite invites est la classe parent de Etudiant et de Personnel
 */

public abstract class Invites implements Serializable {
    private int numero;
    private String nom;
    private String prenom;
    private String numTel;
    private String email;
    String role;

    public String getRole() {
        return role;
    }

    public Invites(int numero, String nom, String prenom, String numTel, String email) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.email = email;
        this.role = "Invite" ;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}






