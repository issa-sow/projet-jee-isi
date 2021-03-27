package sow.issa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB {
    public static Connection getConnection(){
        Connection connection = null;
        /*try {
            Class.forName( "com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver 'mysql' introuvable : " + e.getMessage());
        }*/

        try {
            connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/vente_produit", "issa", "issa");
        } catch (SQLException e) {
            System.out.println("Imopssible de se connecter à la base de données 'vente_produit' : " + e.getMessage());
        }

        return connection;
    }
}
