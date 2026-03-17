package com.github.joao;

import java.sql.*;

public class PessoaDAO {
    // Vai retornar o id cadastrado, para isso usamos o RETURN_GENERATED_KEYS no prepareStatement
    public int cadastrar(Pessoa p) {
        String sql = "INSERT INTO tb_pessoa (nome_pessoa, cpf, telefone, endereco_pessoa) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stm.setString(1, p.getNome());
            stm.setString(2, p.getCpf());
            stm.setString(3, p.getTelefone());
            stm.setString(4, p.getEndereco());

            stm.executeUpdate();

            // Obtemos a chave gerada. O rs.next é essencial para obter valor de uma coluna, nesse caso ele obtem apenas a primeira
            try (ResultSet rs = stm.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao tentar cadastrar pessoa" + e);
        }
        // Caso ocorra algum problema
        return -1;
    }
}
