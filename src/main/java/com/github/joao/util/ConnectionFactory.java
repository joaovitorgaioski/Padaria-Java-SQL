package com.github.joao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe que realiza conexão com o banco
public class ConnectionFactory {

    private static final String URL = "jdbc:mariadb://localhost:3306/db_padaria";
    private static final String USER = "root";
    private static final String PASS = "mysql";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco da padaria: ", e);
        }
    }
}
