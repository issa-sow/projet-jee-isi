package sow.issa;

public abstract class Personne {
    protected Integer id;
    protected String nom;
    protected String prenom;
    protected String dateNais;
    protected String adresse;
    protected String telephone;
    
    public Personne() {
        this.id = null;
        this.nom = null;
        this.prenom = null;
        this.dateNais = null;
        this.adresse = null;
        this.telephone = null;
    }

    public Personne(Integer id, String nom, String prenom, String dateNais, String adresse, String telephone) {
        this.id= id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNais = dateNais;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "\t\t\tid='" + id + "'\n" +
                "\t\t\tnom='" + nom + "'\n" +
                "\t\t\tprenom='" + prenom + "'\n"  +
                "\t\t\tdateNais='" + dateNais + "'\n" +
                "\t\t\tadresse='" + adresse + "'\n" +
                "\t\t\telephone='" + telephone + "'" ;
    }
}
