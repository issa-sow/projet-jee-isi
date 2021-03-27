package sow.issa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Commande {
    private Integer idCommande;
    private String dateCommande;
    private Client client;

    public Commande() {
        this.idCommande = null;
        this.dateCommande = null;
        this.client = new Client();
    }

    public Commande(Integer idCommande, String dateCommande, Client client) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.client = client;
    }

    public Integer getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(Integer idCommande) {
        this.idCommande = idCommande;
    }

    public String getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(String dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String toString() {
        return "Commande {\n" +
                "\t\tidCommande=" + idCommande + "\n" +
                "\t\tdatecommande=" + dateCommande + "\n" +
                client.toString() + "\n" +
                "\n}";
    }
}
