package com.github.joao.control;

import com.github.joao.dao.FuncionarioDAO;
import com.github.joao.dao.PessoaDAO;
import com.github.joao.model.Funcionario;

public class FuncionarioController {
    private FuncionarioDAO dao;

    public FuncionarioController() {
        this.dao = new FuncionarioDAO();
    }

    public void cadastrar(Funcionario f) {
        // Geramos a chave em Pessoa e obtivemos ela aqui para cadastrar Funcionario
        int chaveGerada = new PessoaDAO().cadastrar(f);
        f.setId(chaveGerada);

        dao.cadastrar(f);
    }
}
