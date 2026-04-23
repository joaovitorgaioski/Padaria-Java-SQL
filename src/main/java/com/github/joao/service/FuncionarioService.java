package com.github.joao.service;

import com.github.joao.dao.FuncionarioDAO;
import com.github.joao.dao.PessoaDAO;
import com.github.joao.model.Funcionario;
import com.github.joao.model.TipoPonto;

import java.util.List;

public class FuncionarioService {
    private FuncionarioDAO dao;

    public FuncionarioService() {
        this.dao = new FuncionarioDAO();
    }

    public void cadastrar(Funcionario f) {
        // Geramos a chave em Pessoa e obtivemos ela aqui para cadastrar Funcionario
        int chaveGerada = new PessoaDAO().cadastrar(f);
        f.setId(chaveGerada);

        dao.cadastrar(f);
    }

    public List<Funcionario> listar() {
        return dao.listar();
    }

    public int buscarIdPorCpf(String cpf) {
        return dao.buscarIdPorCpf(cpf);
    }

    public TipoPonto buscarTipoPontoAtual(int id) {
        return dao.buscarTipoPontoAtual(id);
    }
}
