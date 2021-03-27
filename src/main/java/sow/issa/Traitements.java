package sow.issa;

import sun.java2d.pipe.SpanShapeRenderer;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Traitements {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    Stock stock = Stock.getInsatance();
    Scanner sc = new Scanner(System.in);
    /**
     * Partie concernant l'administrateur
     * c'est cette fonction qui gére l'ajout d'un fournisseur ou d'un client
     */
    public void insertPersonne(){
        System.out.println("*******[AJOUT D'UN FOURNISSEUR OU D'UN CLIENT]*******");
        char response = ' ', choix = ' ';
        do {
            do {
                System.out.println("Voulez-vous ajoiuter :");
                System.out.println("\t F: un fournisseur");
                System.out.println("\t C: un client");
                choix = sc.nextLine().charAt(0);
            } while(choix != 'F' && choix != 'C');

            if(choix == 'F') {
                System.out.println("****************[ FOURNISSEUR ]***************");
                Fournisseur fournisseur = getInformationFournisseur();
                insertFournisseur(fournisseur);
            } else {
                System.out.println("****************[ CLIENT ]***************");
                Client client = getInformationClient();
                insertClient(client);
            }

            do {
                System.out.println("Souhaitez-vous continuer à ajouter un fournisseur/client (O/N) ?");
                response = sc.nextLine().charAt(0);
            } while (response != 'O' && response != 'N');
        }while (response == 'O');
    }

    /**
     * les methodes getInformationFournisseur et getInformationClient ainsi que insertFournisseur et insertClient
     * nous évite d'avoir à dupliquer les mêmes bout
     * de codes à chaque fois qu'on souhaite inserer un fournisseur ou un client
     */
    public int insertFournisseur(Fournisseur fournisseur) {
        connection = ConnexionDB.getConnection();
        int idFourniseur = -1;
        String sql = "INSERT INTO Fournisseur VALUES(NULL, ?, ?, ?, ?, ?);";
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, fournisseur.getNom());
            statement.setString(2, fournisseur.getPrenom());
            statement.setDate(3, Date.valueOf(fournisseur.getDateNais()));
            statement.setString(4, fournisseur.getAdresse());
            statement.setString(5, fournisseur.getTelephone());
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            while (resultSet.next())
                idFourniseur = resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("L'insetion du fournisseur dans la base a échoué");
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Un probléme est survenu lors de la frmeture de la connexion");
            }
        }

        return idFourniseur;
    }

    public int insertClient(Client client) {
        connection = ConnexionDB.getConnection();
        String sql = "INSERT INTO Client VALUES(NULL, ?, ?, ?, ?, ?);";
        int idClient = -1;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setDate(3, Date.valueOf(client.getDateNais()));
            statement.setString(4, client.getAdresse());
            statement.setString(5, client.getTelephone());
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            while (resultSet.next())
                idClient = resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("L'insetion du client dans la base a échoué");
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Un probléme est survenu lors de la frmeture de la connexion");
            }
        }

        return idClient;
    }

    public int insertProduit(Produit produit) {
        connection = ConnexionDB.getConnection();
        String sql = "INSERT INTO Produit VALUES(NULL, ?, ?, ?, ?, ?, ?);";
        int idProduit = -1;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, produit.getNomProduit());
            statement.setString(2, produit.getMarque());
            statement.setString(3, produit.getModele());
            statement.setDouble(4, produit.getPrix());
            statement.setInt(5, produit.getQuantite());
            statement.setInt(6, produit.getFournisseur().getId());
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            while (resultSet.next())
                idProduit = resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("L'insetion du produit dans la base a échoué");
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Un probléme est survenu lors de la frmeture de la connexion");
            }
        }

        return idProduit;
    }

    public int insertCommande(int idClient) {
        connection = ConnexionDB.getConnection();
        String sql = "INSERT INTO Commande VALUES(NULL, NOW(), ?);";
        int idCommande = -1;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idClient);
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            while (resultSet.next())
                idCommande = resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("L'insetion de la commande dans la base a échoué");
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Un probléme est survenu lors de la frmeture de la connexion");
            }
        }

        return idCommande;
    }

    public int insertFacture(int idCommande, int idProduit, int quantite) {
        connection = ConnexionDB.getConnection();
        String sql = "INSERT INTO Facture VALUES(NULL, ?, ?, ?);";
        int idFacture = -1;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, idCommande);
            statement.setInt(2, idProduit);
            statement.setInt(3, quantite);
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            while (resultSet.next())
                idFacture = resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println("L'impression de la facture a échoué");
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Un probléme est survenu lors de la frmeture de la connexion");
            }
        }

        return idFacture;
    }
    
    /**
     * La memthode addProduct permet d'ajouter un/des produit(s)
     * les produit sont ajouter au stock(la classe Stock implemente le pattern SINGLETON)
     * L'objectif est d'assurer qu'un seul stock sera créer et d'y ajouter les nouveaux produits
     */
    public void addProduct(){
        System.out.println("*******************[ AJOUT DE PRODUIT ]*********************");
        char response = ' ';
        do {
            Produit produit = getInformationProduit();
            int idFournisseur = insertFournisseur(produit.getFournisseur());
            produit.getFournisseur().setId(idFournisseur);
            insertProduit(produit);
            //stock.getAllProduct().add(p);

            do {
                System.out.println("Voulez-vous continuer à ajouter des produits (O/N) ?");
                response = sc.nextLine().charAt(0);
            } while (response != 'O' && response != 'N');

        } while (response == 'O');
    }

    public Fournisseur getInformationFournisseur() {
        Fournisseur f = new Fournisseur();
        System.out.print("Saisissez le nom du fournissuer : ");
        f.setNom(sc.nextLine());
        System.out.print("Saisissez le prenom nom du fournissuer : ");
        f.setPrenom(sc.nextLine());
        System.out.print("Saisissez la date de naissance nom du fournissuer : ");
        String dateNais = sc.nextLine(), y, m, d;
        d = dateNais.substring(0, 2);
        m = dateNais.substring(3, 5) + '-';
        y = dateNais.substring(6) + '-';
        f.setDateNais(y + m + d);
        System.out.print("Saisissez l'adresse  : ");
        f.setAdresse(sc.nextLine());
        System.out.print("Saisissez le numéro de téléphone nom du fournissuer : ");
        f.setTelephone(sc.nextLine());

        return f;
    }

    public Client getInformationClient() {
        Client c = new Client();
        System.out.print("Saisissez le nom du client: ");
        c.setNom(sc.nextLine());
        System.out.print("Saisissez le prenom du client: ");
        c.setPrenom(sc.nextLine());
        System.out.print("Saisissez la date de naissance du client: ");
        String dateNais = sc.nextLine(), y, m, d;
        d = dateNais.substring(0, 2);
        m = dateNais.substring(3, 5) + '-';
        y = dateNais.substring(6) + '-';
        c.setDateNais(y + m + d);
        System.out.print("Saisissez l'adresse  : ");
        c.setAdresse(sc.nextLine());
        System.out.print("Saisissez le numéro de téléphone du client: ");
        c.setTelephone(sc.nextLine());

        return  c;
    }

    public Produit getInformationProduit() {
        Produit p = new Produit();
        p.setFournisseur(getInformationFournisseur());
        System.out.print("Donnez le nom du produit : ");
        p.setNomProduit(sc.nextLine());
        System.out.print("Donnez la marque : ");
        p.setMarque(sc.nextLine());
        System.out.print("Donnez le modéle : ");
        p.setModele(sc.nextLine());
        System.out.print("Donnez le prix fixe : ");
        p.setPrix(sc.nextDouble());
        sc.nextLine();
        System.out.print("Donnez la quantité : ");
        p.setQuantite(sc.nextInt());
        sc.nextLine();

        return  p;
    }

    /**
     * La methode listFournisseur liste tous les fournisseurs ajouter
     */
    public void listFournisseur(){
        System.out.println("***********[ LA LISTE DE TOUS LES FOURNISSEURS ]**************");
        List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
        connection = ConnexionDB.getConnection();
        String sql = "SELECT * FROM Fournisseur;";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Fournisseur f = new Fournisseur(
                        resultSet.getInt("idFournisseur"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        String.valueOf(resultSet.getDate("dateNais")),
                        resultSet.getString("adresse"),
                        resultSet.getString("telephone")
                );

                fournisseurs.add(f);
            }
        } catch (SQLException e) {
            System.out.println("L'insetion du fournisseur dans la base a échoué");
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Un probléme est survenu lors de la frmeture de la connexion");
            }
        }

        System.out.println("[");
        for(Fournisseur f : fournisseurs){
            System.out.println(f.toString());
        }
        System.out.println("]");
    }

    /**
     * La methode listClient liste tous les client ajouter
     */
    public void listClient(){
        System.out.println("***********[ LA LISTE DE TOUS LES CLIENTS ]**************");
        List<Client> clients  = new ArrayList<Client>();
        connection = ConnexionDB.getConnection();
        String sql = "SELECT * FROM Client;";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Client c = new Client(
                        resultSet.getInt("idClient"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        String.valueOf(resultSet.getDate("dateNais")),
                        resultSet.getString("adresse"),
                        resultSet.getString("telephone")
                );

                clients.add(c);
            }
        } catch (SQLException e) {
            System.out.println("L'insetion du client dans la base a échoué");
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Un probléme est survenu lors de la frmeture de la connexion");
            }
        }

        System.out.println("[");
        for(Client c : clients){
            System.out.println(c.toString());
        }
        System.out.println("]");
    }

    /**
     * La methode listProduct liste tous les produits ajouter
     */
    public void listProduct() {
        System.out.println("***********[ LA LISTE DE TOUS LES PORDUITS ]**************");
        List<Produit> allProducts  = new ArrayList<Produit>();
        connection = ConnexionDB.getConnection();
        String sql = "SELECT * FROM Produit;";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Produit p = new Produit(
                        resultSet.getInt("idProduit"),
                        resultSet.getString("nom"),
                        resultSet.getString("marque"),
                        resultSet.getString("modele"),
                        resultSet.getDouble("prix"),
                        resultSet.getInt("quantite"),
                        new Fournisseur()
                );
                p.getFournisseur().setId(resultSet.getInt("idFournisseur"));
                allProducts.add(p);
            }
            /*resultSet.close();
            resultSet.close();
            connection.close();*/

            for(Produit p : allProducts) {
                Personne per = researchPersone("Fournisseur", p.getFournisseur().getId());
                p.setFournisseur(new Fournisseur(
                        per.id, per.nom, per.prenom, per.dateNais, per.adresse, per.telephone
                ));
            }

        } catch (SQLException e) {
            System.out.println("L'insetion du client dans la base a échoué");
        } finally {
            try {
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Un probléme est survenu lors de la frmeture de la connexion");
            }
        }

        System.out.println("[");
        for(Produit p : allProducts) {
            System.out.println(p);
        }
        System.out.println("]");
    }

    /**
     * Methode de recherche d'un client ou d'un fournisseur
     */
    public Personne researchPersone(String table, int id) {
        Personne p = null;
        if (table.equalsIgnoreCase("fournisseur")) {
            p = new Fournisseur();
            connection = ConnexionDB.getConnection();
            String sql = "SELECT * FROM fournisseur WHERE idFournisseur = ?;";
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    p.setId(resultSet.getInt("idFournisseur"));
                    p.setNom(resultSet.getString("nom"));
                    p.setPrenom(resultSet.getString("prenom"));
                    p.setDateNais(sdf.format(resultSet.getDate("dateNais")));
                    p.setAdresse(resultSet.getString("adresse"));
                    p.setTelephone(resultSet.getString("telephone"));
                }
            } catch (SQLException e) {
                System.out.println("La requête à échoué : " + e.getMessage());
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    System.out.println("La fermeture de la connexion à échoué : " + e.getMessage());
                }
            }
        } else {
            p = new Client();
            connection = ConnexionDB.getConnection();
            String sql = "SELECT * FROM client WHERE idClient = ?;";
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    p.setId(resultSet.getInt("idClient"));
                    p.setNom(resultSet.getString("nom"));
                    p.setPrenom(resultSet.getString("prenom"));
                    p.setDateNais(sdf.format(resultSet.getDate("dateNais")));
                    p.setAdresse(resultSet.getString("adresse"));
                    p.setTelephone(resultSet.getString("telephone"));
                }
            } catch (SQLException e) {
                System.out.println("La requête à échoué : " + e.getMessage());
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    System.out.println("La fermeture de la connexion à échoué" + e.getMessage());
                }
            }
        }
        return p;
    }

    /**
     * Methode de recherche d'un produit
     */
    public Produit researchProduct(String nomProduit) {
        Produit p = null;
        connection = ConnexionDB.getConnection();
        String sql = "SELECT * FROM produit WHERE nom = ?;";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, nomProduit);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                p = new Produit();
                p.setIdProduit(resultSet.getInt("idProduit"));
                p.setNomProduit(resultSet.getString("nom"));
                p.setMarque(resultSet.getString("marque"));
                p.setModele(resultSet.getString("modele"));
                p.setPrix(resultSet.getDouble("prix"));
                p.setQuantite(resultSet.getInt("quantite"));
                p.setFournisseur(new Fournisseur());
                p.getFournisseur().setId(resultSet.getInt("idFournisseur"));
            }
        } catch (SQLException e) {
            System.out.println("La requête à échoué : " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("La fermeture de la connexion à échoué : " + e.getMessage());
            }
        }

        return p;
    }

    public Commande researchCommande(Commande c) {
        connection = ConnexionDB.getConnection();
        String sql = "SELECT * FROM Commande WHERE idCommande = ?;";
        Commande com = null;
        SimpleDateFormat sdf = new SimpleDateFormat("DD-MM-YYYY HH:MM:SS");
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, c.getIdCommande());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                com = new Commande();
                com.setIdCommande(resultSet.getInt("idCommande"));
                com.setDateCommande(sdf.format(resultSet.getDate("dateCommande")));
                com.setClient(new Client());
                com.getClient().setId(resultSet.getInt("idClient"));
            }
            if (com != null) {
                Personne per = researchPersone("client", com.getClient().getId());
                Client cli = new Client(per.id, per.nom, per.prenom, per.dateNais, per.adresse, per.telephone);
                com.setClient(cli);
            }

        } catch (SQLException e) {
            System.out.println("La requête à échoué : " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("La fermeture de la connexion à échoué : " + e.getMessage());
            }
        }

        return com;
    }

    /**
     * La methode passerCommande permet à un client de passer une commande
     */
    public void passerCommade() {
        System.out.println("******************[ COMMANDER UN/DES PRODUIT ]***********************");
        System.out.println("\t BIENVENUE CHER CLIENT! VOICI LA LISTE DE TOUT NOS PRODUIT DISPONIBLE");
        listProduct();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy HH:MM:SS");
        char response = ' ', addProduct = ' ';
        boolean bool = true;
        String choixProduit;
        do {
            do {
                System.out.println("Souhaitez-vous passer une commande (O/N) ?");
                response = sc.nextLine().charAt(0);
            } while (response  != 'O' && response != 'N');

            if (response == 'O') {
                Client client = getInformationClient();
                client.setId(insertClient(client));
                Commande com = new Commande();
                com.setIdCommande(insertCommande(client.getId()));
                com.setDateCommande(sdf.format(new Timestamp(System.currentTimeMillis())));
                com.setClient(client);
                Produit prod = null;
                List<Facture> allFact = new ArrayList<Facture>();

                do {
                    System.out.print("Veuillez choisir un produit existant : ");
                    choixProduit = sc.nextLine();
                    prod = researchProduct(choixProduit);

                    if (prod == null) {
                        System.out.println("Désolé mais ce produit n'est pas disponible !");
                        bool = false;
                    } else {
                        //On insere la facture
                        Facture fact = new Facture();
                        System.out.println("Veuiller saisir la quantitié : ");
                        fact.setQuantite(sc.nextInt());
                        fact.setIdFacture(insertFacture(com.getIdCommande(), prod.getIdProduit(), fact.getQuantite()));
                        fact.setCommande(com);
                        fact.getProduits().add(prod);
                        allFact.add(fact);

                        sc.nextLine();
                        System.out.println("Voulez-vous ajouter un autre produit (O/N) ?");
                        addProduct = sc.nextLine().charAt(0);

                        if (addProduct == 'O') bool = true;
                        else bool = false;
                    }
                } while (bool == true);

                if (allFact.size() > 0) {
                    /** S'il y'a au moins une commande
                     * On affiche au(x) client(s) sa/ses facture(s) : le(s) client(s) ainsi que sa/ses commande(s)
                     */
                    for (Facture fact : allFact) {
                        System.out.println(fact);
                    }
                } else {
                    deleteCommande(com);
                    deleteClient(com.getClient().getId());
                }
            }
        } while ( response == 'O');
    }

    public Client deleteClient(int idClient) {
        Personne per = researchPersone("client", idClient);
        Client cli = null;
        if(per != null) {
            cli = new Client(per.id, per.nom, per.prenom, per.dateNais, per.adresse, per.telephone);
            connection = ConnexionDB.getConnection();
            String sql = "DELETE FROM client WHERE idClient = ?;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, cli.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("La  suppression du client a échoué");
            } finally {
                try {
                    if(statement != null) statement.close();
                    if(connection != null) connection.close();
                } catch (SQLException e) {
                    System.out.println("Un probléme est survenu lors de la fermeture de la connexion");
                }
            }
        }
        return cli;
    }

    public Fournisseur deleteFournisseur(int idFournisseur) {
        Personne per = researchPersone("fournisseur", idFournisseur);
        Fournisseur four = null;
        if(per != null) {
            four = new Fournisseur(per.id, per.nom, per.prenom, per.dateNais, per.adresse, per.telephone);
            connection = ConnexionDB.getConnection();
            String sql = "DELETE FROM fournisseur WHERE idFournisseur = ?;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, four.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("La  suppression du client a échoué");
            } finally {
                try {
                    if(statement != null) statement.close();
                    if(connection != null) connection.close();
                } catch (SQLException e) {
                    System.out.println("Un probléme est survenu lors de la fermeture de la connexion");
                }
            }
        }
        return four;
    }

    public Commande deleteCommande(Commande commande) {
        Commande com = researchCommande(commande);
        if(com != null) {
            connection = ConnexionDB.getConnection();
            String sql = "DELETE FROM commande WHERE idCommande = ?;";
            try {
                statement = connection.prepareStatement(sql);
                statement.setInt(1, com.getIdCommande());
                statement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("La  suppression de la commande a échoué");
            } finally {
                try {
                    if(statement != null) statement.close();
                    if(connection != null) connection.close();
                } catch (SQLException e) {
                    System.out.println("Un probléme est survenu lors de la fermeture de la connexion");
                }
            }
        }
        return com;
    }

}
