package sow.issa;

import java.util.Date;

public class Fournisseur extends Personne{

    public Fournisseur(){
        super();
    }

    public Fournisseur(Integer id, String nom, String prenom, String dateNais, String adresse, String telephone) {
        super(id, nom, prenom, dateNais, adresse, telephone);
    }

    @Override
    public String toString() {
         return "\t\tFournisseur " +
                "{\n" +
                    super.toString() +
                "\n\t\t}";
    }
}
