package sow.issa;

public class Fournisseur extends Personne{
    public Fournisseur(){
        super();
    }

    public Fournisseur(long id, String nom, String prenom, String dateNais, String telephone) {
        super(id, nom, prenom, dateNais, telephone);
    }

    @Override
    public String toString() {
        return "Fournisseur" + super.toString();
    }
}
