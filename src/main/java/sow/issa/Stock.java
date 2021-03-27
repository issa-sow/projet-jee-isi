package sow.issa;

import java.util.ArrayList;
import java.util.List;

public final class Stock {
    private static Stock instance = null;
    private List<Produit> allProduct;

    static {
        instance = new Stock();
    }

    public final static Stock getInsatance() {
        return instance;
    }

    private Stock(){
         this.allProduct = new ArrayList<Produit>();
    }

    public List<Produit> getAllProduct() {
        return allProduct;
    }

    public void setAllProduct(List<Produit> allProduct) {
        this.allProduct = allProduct;
    }
}
