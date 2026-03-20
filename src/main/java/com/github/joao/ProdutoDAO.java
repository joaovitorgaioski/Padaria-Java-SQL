package com.github.joao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ProdutoDAO {

    // Para implementar o metodo listar, traduzimos de SQL para Objeto
    public List<Produto> listar() {
        // SQL que queremos
        String sql = "SELECT * FROM tb_produto";
        List<Produto> lista = new ArrayList<>();

        try {
            // Conexão, PreparedStatement, ResultSet
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            // rs irá receber a tabela do banco

            while (rs.next()) {
                Produto p = new Produto();

                p.setId(rs.getInt("id_produto_PK"));
                p.setNome(rs.getString("nome_produto"));
                p.setSabor(rs.getString("sabor_produto"));
                p.setQuantidade(rs.getInt("quantidade_produto"));

                lista.add(p);
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println("Erro na leitura do banco!" + e);
        }
        return lista;
    }

    // Para implementar o metodo salvar, traduzimos de Objeto para SQL
    public void salvar(Produto p) {
        // Primeiro tenta realizar incremento com UPDATE
        String sql = "UPDATE tb_produto SET quantidade_produto = quantidade_produto + ? WHERE nome_produto = ? AND sabor_produto = ?";

        // Usei um Try-With-Resources pq ele fecha a conexão automaticamente ao finalizar o escopo do try
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)
        ) {
            // Fazemos o setInt passando qual o índice do SQL que iremos mudar
            stm.setInt(1, p.getQuantidade());
            stm.setString(2, p.getNome().toLowerCase());
            stm.setString(3, p.getSabor().toLowerCase());

            int linhasAfetadas = stm.executeUpdate();

            // Caso não haja mudanças (nenhuma linha foi alterada no UPDATE), realiza o INSERT
            if (linhasAfetadas == 0) {
                // Não passei id_produto_PK, pois ele é auto_increment no banco
                sql = "INSERT INTO tb_produto (nome_produto, sabor_produto, quantidade_produto) VALUES (?, ?, ?)";

                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, p.getNome().toLowerCase());
                    stmt.setString(2, p.getSabor().toLowerCase());
                    stmt.setInt(3, p.getQuantidade());

                    // Executa a ação no banco
                    stmt.executeUpdate();
                }
            }
            System.out.println(linhasAfetadas > 0 ? "Produto incrementado no estoque com sucesso!" : "Produto inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao tentar salvar o produto!" + e);
        }
    }
}