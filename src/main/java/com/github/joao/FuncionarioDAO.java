package com.github.joao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionarioDAO {
    // Geramos a chave em Pessoa e obtivemos ela aqui para cadastrar Cliente
    public void cadastrar(Funcionario f) {
        PessoaDAO pDAO = new PessoaDAO();
        int chaveGerada = pDAO.cadastrar(f);
        if (chaveGerada == -1) {
            System.out.println("Não foi possível cadastrar o funcionário");
            return;
        }

        String sql = "INSERT INTO tb_funcionario (id_pessoa_PK_FK, horario_de_trabalho, salario) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)
        ) {
            stm.setInt(1, chaveGerada);
            stm.setInt(2, f.getHorarioTrabalho());
            stm.setDouble(3, f.getSalario());

            stm.executeUpdate();
            System.out.println("Funcionário cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao tentar cadastrar cliente" + e);
        }
    }
}
