package com.github.joao.control;

import com.github.joao.dao.FuncionarioDAO;
import com.github.joao.dao.PessoaDAO;
import com.github.joao.model.Funcionario;

public class FuncionarioController {
    private FuncionarioDAO dao;

    public FuncionarioController() {
        this.dao = new FuncionarioDAO();
    }

    public int cadastrar(Funcionario f) {
        // Geramos a chave em Pessoa e obtivemos ela aqui para cadastrar Funcionario
        PessoaDAO pDAO = new PessoaDAO();
        int chaveGerada = pDAO.cadastrar(f);
        if (chaveGerada == -1) return -1;

        f.setId(chaveGerada);

        return dao.cadastrar(f);
    }
}
