package sow.issa;

import java.util.Date;

public abstract class Personne {
    protected long id;
    protected String nom;
    protected String prenom;
    protected String dateNais;
    protected String telephone;
    
    public Personne() {
        this.id = 0;
        this.nom = null;
        this.prenom = null;
        this.dateNais = null;
        this.telephone = null;
    }

    public Personne(long id, String nom, String prenom, String dateNais, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNais = dateNais;
        this.telephone = telephone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDateNais() {
        return dateNais;
    }

    public void setDateNais(String dateNais) {
        this.dateNais = dateNais;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNais=" + dateNais +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
