package myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseDAO {

    // Méthode pour se connecter à la base de données et exécuter une requête
    public void executeQuery() {
        Connection con = null;
        ResultSet rst = null;
        Statement stmt = null;

        try {
            // Chargement du driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");  // Assurez-vous d'utiliser le bon driver MySQL

            // Informations de connexion
            String serveur = "mysql.exemple.com";
            String login = "toto";
            String motDePasse = "123456";
            String schema = "mabase";
            String url = "jdbc:mysql://" + serveur + "/" + schema + "?user=" + login + "&password=" + motDePasse;

            // Connexion à la base de données
            con = DriverManager.getConnection(url);

            // Création de l'objet Statement
            stmt = con.createStatement();

            // Exécution de la requête
            rst = stmt.executeQuery("SELECT * FROM etudiants WHERE nom = 'Dupont'");

            // Récupération et affichage des résultats
            while (rst.next()) {
                String prenom = rst.getString("prenom");
                System.out.println(prenom);  // Affiche le prénom des étudiants
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        } finally {
            try {
                // Fermeture des ressources
                if (rst != null) rst.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (Exception e) {
                System.out.println("Erreur de fermeture des ressources : " + e.getMessage());
            }
        }
    }
}
