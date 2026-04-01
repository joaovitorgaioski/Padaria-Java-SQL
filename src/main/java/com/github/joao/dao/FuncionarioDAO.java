package com.github.joao.dao;

import com.github.joao.model.Funcionario;
import com.github.joao.util.DatabaseHelper;

public class FuncionarioDAO {

    public int cadastrar(Funcionario f) {

        String sql = "INSERT INTO tb_funcionario (id_pessoa_PK_FK, horario_de_trabalho, salario) VALUES (?, ?, ?)";

        return DatabaseHelper.executeCommand(sql, f.getId(), f.getHorasTrabalho(), f.getSalario());
    }
}
