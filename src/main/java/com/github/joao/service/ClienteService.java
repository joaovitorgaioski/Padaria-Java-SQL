package com.github.joao.service;

import com.github.joao.dao.ClienteDAO;
import com.github.joao.dao.PessoaDAO;
import com.github.joao.model.Cliente;

import java.util.List;

public class ClienteService {
    private ClienteDAO dao;

    public ClienteService() {
        this.dao = new ClienteDAO();
    }

    public void cadastrar(Cliente c) {
        // Geramos a chave em Pessoa e obtivemos ela aqui para cadastrar Cliente. Caso de erro, a excessão já é lançada
        int chaveGerada = new PessoaDAO().cadastrar(c);
        c.setId(chaveGerada);

        dao.cadastrar(c);
    }

    public List<Cliente> listar() {
        return dao.listar();
    }
}
