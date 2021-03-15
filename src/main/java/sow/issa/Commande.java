package sow.issa;

public class Commande {
    private long idCommande;
    private String dateCommande;
    private Client client;

    public Commande() {
        this.idCommande = 0;
        this.dateCommande = null;
        this.client = new Client();
    }

    public Commande(long idCommande, String dateCommande, Client client) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.client = client;
    }

    public long getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(long idCommande) {
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

    @Override
    public String toString() {
        return "Commande{" +
                "idCommande=" + idCommande +
                ", dateCommande='" + dateCommande + '\'' +
                client.toString() +
                '}';
    }
}
