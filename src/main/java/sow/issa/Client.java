package sow.issa;

import java.util.Date;

public class Client extends Personne{

    public Client(){
        super();
    }

    public Client(Integer id, String nom, String prenom, String dateNais, String adresse, String telephone) {
        super(id, nom, prenom, dateNais, adresse, telephone);
    }

    @Override
    public String toString() {
        return "\t\tClient " +
                "{\n" +
                    super.toString() +
                "\n\t\t}";

    }
}
