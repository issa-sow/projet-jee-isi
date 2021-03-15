package sow.issa;

public class Produit {
    private long idProduit;
    private String nomProduit;
    private double prix;
    private String marque;
    private String modele;

    public Produit() {
        this.idProduit = 0;
        this.nomProduit = null;
        this.prix = 0;
        this.marque = null;
        this.modele = null;
    }

    public Produit(long idProduit, String nomProduit, double prix, String marque, String modele) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.prix = prix;
        this.marque = marque;
        this.modele = modele;
    }

    public long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(long idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "idProduit=" + idProduit +
                ", nomProduit='" + nomProduit + '\'' +
                ", prix=" + prix +
                ", marque=" + marque +
                ", modele='" + modele + '\'' +
                '}';
    }
}
