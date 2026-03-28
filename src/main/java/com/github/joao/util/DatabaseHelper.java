package com.github.joao.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe responsável por gerenciar comandos de inserção / alteração e de busca. Ótimo para evitar repetição de "try"
public class DatabaseHelper {

    public static int executeCommand(String sql, Object... param) {
        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stm = conn.prepareStatement(sql)
        ) {

            // Vamos percorrer os índices de cada parâmetro e executar o set adequado
            for (int i = 0; i < param.length; i++) {
                stm.setObject(i + 1, param[i]);
            }

            return stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro na interação com o banco!" + e);
        }
        return 0;
    }

    public static List<Map<String, Object>> executeQuery(String sql, Object... param) {
        List<Map<String, Object>> lista = new ArrayList<>();

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stm = conn.prepareStatement(sql)
        ) {

            for (int i = 0; i < param.length; i++) {
                stm.setObject(i + 1, param[i]);
            }

            try (ResultSet rs = stm.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> linha = new HashMap<>();

                    for (int i = 1; i <= columnCount; i++) {
                        linha.put(metaData.getColumnName(i), rs.getObject(i));
                    }
                    lista.add(linha);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro na interação com o banco!" + e);
        }
        return lista;
    }
}
