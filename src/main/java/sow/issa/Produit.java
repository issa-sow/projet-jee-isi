package sow.issa;

public class Produit {
    private Integer idProduit;
    private String nomProduit;
    private String marque;
    private String modele;
    private double prix;
    private int quantite;
    private Fournisseur fournisseur;

    public Produit() {
        this.idProduit = null;
        this.nomProduit = null;
        this.marque = null;
        this.modele = null;
        this.prix = 0;
        this.quantite = 0;
        this.fournisseur = null;
    }

    public Produit(Integer idProduit, String nomProduit, String marque, String modele, double prix, int quantite, Fournisseur fournisseur) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.marque = marque;
        this.modele = modele;
        this.prix = prix;
        this.quantite = quantite;
        this.fournisseur = fournisseur;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override
    public String toString() {
        return "\tProduit {\n" +
                "\t\tidProduit='" + idProduit + "'\n" +
                "\t\tnomProduit='" + nomProduit + "'\n" +
                "\t\tmarque='" + marque + "'\n" +
                "\t\tmodele='" + modele + "'\n" +
                "\t\tprix='" + prix + "'\n" +
                "\t\tquantité='" + quantite + "'\n" +
                fournisseur.toString() + "\n" +
                "\t}";
    }
}
