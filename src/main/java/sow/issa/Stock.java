package sow.issa;

import java.util.ArrayList;
import java.util.List;

public final class Stock {
    private static Stock insatance = null;
    private List<Produit> allProduct = new ArrayList<Produit>();
    static {
        insatance = new Stock();
    }

    public final static Stock getInsatance() {
        return insatance;
    }

    private Stock(){ }

    public List<Produit> getAllProduct() {
        return allProduct;
    }

    public void setAllProduct(List<Produit> allProduct) {
        this.allProduct = allProduct;
    }
}
