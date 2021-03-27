package sow.issa;

import java.util.ArrayList;
import java.util.List;

public class Facture {
    private Integer idFacture;
    private Commande commande;
    private List<Produit> produits;
    private int quantite;

    public Facture () {
        this.idFacture = null;
        this.commande = null;
        this.produits = new ArrayList<Produit>();
    }

    public Facture (Integer idFacture, Commande commande, List<Produit> produits, int quantite) {
        this.idFacture = idFacture;
        this.commande = commande;
        for (Produit p : produits) this.produits.add(p);

        this.quantite = quantite;
    }

    public Integer getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        String prod = "";
        for (Produit p : produits) prod += p.toString();
        return "Facture {\n" +
                "\tidFacture='" + idFacture + "'\n" +
                "\t" + commande.toString() + "\n" +
                prod + "\n" +
                "\tquantite='" + quantite + "'\n" +
                '}';
    }
}
