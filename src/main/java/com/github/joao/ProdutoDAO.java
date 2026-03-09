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

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro na leitura do banco" + e);
        }

        return lista;
    }

    // Para implementar o metodo salvar, traduzimos de Objeto para SQL
    public void salvar(Produto p) {
        // Não passei id_produto_PK pois ele é auto_increment no banco
        String sql = "INSERT INTO tb_produto (nome_produto, sabor_produto) VALUES (?, ?)";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);

            // Fazemos o setString passando qual o índice do SQL que iremos mudar
            stm.setString(1, p.getNome());
            stm.setString(2, p.getSabor());

            // Executa a ação no banco
            stm.executeUpdate();
            System.out.println("Produto salvo com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao tentar salvar o produto" + e);
        }
    }
}
