package com.github.joao.control;

import com.github.joao.dao.ClienteDAO;
import com.github.joao.dao.PessoaDAO;
import com.github.joao.model.Cliente;

public class ClienteController {
    private ClienteDAO dao;

    public ClienteController() {
        this.dao = new ClienteDAO();
    }

    public void cadastrar(Cliente c) {
        // Geramos a chave em Pessoa e obtivemos ela aqui para cadastrar Cliente. Caso de erro, a excessão já é lançada
        int chaveGerada = new PessoaDAO().cadastrar(c);
        c.setId(chaveGerada);

        dao.cadastrar(c);
    }
}
