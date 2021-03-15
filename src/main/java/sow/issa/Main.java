package sow.issa;

import java.text.DateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Client cli = new Client(1, "sow", "issa", "05-04-1998", "774878028");
        System.out.println(cli.toString());

        Stock st = Stock.getInsatance();
        st.getAllProduct().add(new Produit(1, "Ordinateur", 350000, "ASUS", "ASUS VivoBook"));

        /*for(Produit p : st.getAllProduct()){
            System.out.println(p.toString());
        }*/

        Stock st1 = Stock.getInsatance();
        st1.getAllProduct().add(new Produit(2, "Ordinateur", 350000, "ASUS", "ASUS VivoBook"));
        for(Produit p : st.getAllProduct()) {
            System.out.println(p.toString());
        }
    }
}
