package com.github.joao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {
    // Geramos a chave em Pessoa e obtivemos ela aqui para cadastrar Cliente
    public void cadastrar(Cliente c) {
        PessoaDAO pDAO = new PessoaDAO();
        int chaveGerada = pDAO.cadastrar(c);
        if (chaveGerada == -1) {
            System.out.println("Não foi possível cadastrar o cliente");
            return;
        }

        String sql = "INSERT INTO tb_cliente (id_pessoa_PK_FK, filiacao) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)
        ) {
            stm.setInt(1, chaveGerada);
            stm.setInt(2, c.getFiliacao());

            stm.executeUpdate();
            System.out.println(c.getFiliacao() == 0 ? "Cliente cadastrado com sucesso!" : "Cliente filiado cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao tentar cadastrar cliente" + e);
        }
    }
}
