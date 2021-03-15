package sow.issa;

import java.util.Date;

public class Client extends Personne{
    public Client(){
        super();
    }

    public Client(long id, String nom, String prenom, String dateNais, String telephone) {
        super(id, nom, prenom, dateNais, telephone);
    }

    @Override
    public String toString() {
        return "Client" + super.toString();
    }
}
