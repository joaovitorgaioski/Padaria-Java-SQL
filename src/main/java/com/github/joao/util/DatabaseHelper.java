package com.github.joao.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe responsável por gerenciar comandos de inserção / alteração e de busca. Ótimo para evitar repetição de "try"
public class DatabaseHelper {

    /**
     * Metodo auxiliar para executar comandos SQL que não sejam de buscar (query).
     * Evita repetição de try-catch e simplifica o uso do JDBC.
     * @param sql Comando SQL
     * @param param Eventuais parâmetros para esse comando
     * @return Quantidade de linhas afetadas
     */
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
            throw new RuntimeException("Erro na interação com o banco! ", e);
        }
    }

    /**
     * Metodo auxiliar para executar comandos de busca SQL (query) onde o retorno são múltiplos valores.
     * Evita repetição de try-catch e simplifica o uso do JDBC.
     * @param sql Comando SQL
     * @param param Eventuais parâmetros para esse comando
     * @return Lista com todas as linhas da consulta mapeadas com CHAVE (coluna da tabela) e VALOR (valor daquela coluna)
     */
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
            throw new RuntimeException("Erro na leitura do banco! ", e);
        }
        return lista;
    }

    /**
     * Metodo auxiliar para executar buscas SQL (query) em que deseja-se retornar apenas 1 linha.
     * @param sql Comando SQL
     * @param param Eventuais parâmetros para esse comando
     * @return Mapeamento da query com CHAVE (coluna da tabela) e VALOR (valor daquela coluna)
     */
    public static Map<String, Object> executeQueryUniqueRow(String sql, Object... param) {
        List<Map<String, Object>> result = executeQuery(sql, param);

        return !result.isEmpty() ? result.get(0) : null;
    }

    /**
     * Metodo auxiliar para executar buscas SQL (query) em que deseja-se retornar apenas 1 valor específico.
     * @param sql Comando SQL
     * @param param Eventuais parâmetros para esse comando
     * @return Object do valor único da query ou retorna null caso não encontre nada
     */
    public static Object executeQueryUniqueValue(String sql, Object... param) {
        Map<String, Object> result = executeQueryUniqueRow(sql, param);

        if(result != null && !result.isEmpty())
            return result.values().stream().findFirst().orElse(null);
        return null;
    }
}
