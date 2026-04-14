package com.github.joao.dao;

import com.github.joao.model.Funcionario;
import com.github.joao.util.DatabaseHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FuncionarioDAO {

    public void cadastrar(Funcionario f) {
        String sql = "INSERT INTO tb_funcionario (id_pessoa_PK_FK, horario_trabalho, salario) VALUES (?, ?, ?)";

        DatabaseHelper.executeCommand(sql, f.getId(), f.getHorasTrabalho(), f.getSalario());
    }

    public List<Funcionario> listar() {
        String sql = """
                SELECT tb_pessoa.*, tb_funcionario.salario, tb_funcionario.horario_trabalho
                FROM tb_pessoa JOIN tb_funcionario ON tb_pessoa.id_pessoa_PK = tb_funcionario.id_pessoa_PK_FK
                """;

        List<Map<String, Object>> result = DatabaseHelper.executeQuery(sql);
        List<Funcionario> funcionarios = new ArrayList<>();

        for (Map<String, Object> linha : result) {
            Funcionario f = new Funcionario();

            f.setId((int) linha.get("id_pessoa_PK"));
            f.setNome((String) linha.get("nome"));
            f.setCpf((String) linha.get("cpf"));
            f.setTelefone((String) linha.get("telefone"));
            f.setEndereco((String) linha.get("endereco"));
            f.setSalario((BigDecimal) linha.get("salario"));
            f.setHorasTrabalho((int) linha.get("horario_trabalho"));

            funcionarios.add(f);
        }

        return funcionarios;
    }
}
