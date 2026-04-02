package com.github.joao.dao;

import com.github.joao.model.Cliente;
import com.github.joao.util.DatabaseHelper;

public class ClienteDAO {

    public void cadastrar(Cliente c) {
        String sql = "INSERT INTO tb_cliente (id_pessoa_PK_FK, filiacao) VALUES (?, ?)";

        DatabaseHelper.executeCommand(sql, c.getId(), c.getFiliacao());
    }
}
