package com.github.joao.dao;

import com.github.joao.model.Cliente;
import com.github.joao.model.Produto;
import com.github.joao.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteDAO {

    public void cadastrar(Cliente c) {
        String sql = "INSERT INTO tb_cliente (id_pessoa_PK_FK, filiacao) VALUES (?, ?)";

        DatabaseHelper.executeCommand(sql, c.getId(), c.getFiliacao());
    }

    public List<Cliente> listar() {
        String sql = """
                SELECT tb_pessoa.*, tb_cliente.filiacao
                FROM tb_pessoa JOIN tb_cliente ON tb_pessoa.id_pessoa_PK = tb_cliente.id_pessoa_PK_FK
                """;

        List<Map<String, Object>> result = DatabaseHelper.executeQuery(sql);
        List<Cliente> clientes = new ArrayList<>();

        for (Map<String, Object> linha : result) {
            Cliente c = new Cliente();

            c.setId((int) linha.get("id_pessoa_PK"));
            c.setNome((String) linha.get("nome_pessoa"));
            c.setCpf((String) linha.get("cpf"));
            c.setTelefone((String) linha.get("telefone"));
            c.setEndereco((String) linha.get("endereco_pessoa"));
            c.setFiliacao((int) linha.get("filiacao"));

            clientes.add(c);
        }

        return clientes;
    }
}
