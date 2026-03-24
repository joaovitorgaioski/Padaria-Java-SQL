package poo.a2026_03_13_conexao_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// A camada ConnectionFactory possui o metodo getConnection que permite a conexão com o banco
public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/db_poo";
    private static final String USER = "root";
    private static final String PASS = "mysql";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco" + e);
        }
    }
}
