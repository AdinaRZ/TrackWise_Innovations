package com.example.aplicatieipbuna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/Baza_de_date";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            // Exemplu de interogare
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tabela");
            // ... procesează rezultatul
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
