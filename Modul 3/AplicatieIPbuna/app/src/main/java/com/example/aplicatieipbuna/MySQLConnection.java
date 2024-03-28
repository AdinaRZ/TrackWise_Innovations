package com.example.aplicatieipbuna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://<ip_baza_de_date>:<port>/<nume_baza_de_date>";
    private static final String USER = "<nume_utilizator>";
    private static final String PASSWORD = "<parola>";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            // Exemplu de interogare
            // ResultSet resultSet = statement.executeQuery("SELECT * FROM tabela");
            // ... procesează rezultatul
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
