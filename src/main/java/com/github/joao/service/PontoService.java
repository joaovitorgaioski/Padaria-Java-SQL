package com.github.joao.service;

import com.github.joao.dao.PontoDAO;
import com.github.joao.model.Pessoa;
import com.github.joao.model.Ponto;
import com.github.joao.model.TipoPonto;

import java.util.List;

public class PontoService {
    private PontoDAO dao;

    public PontoService() {
        this.dao = new PontoDAO();
    }

    public List<Ponto> listarRecentes() {
        return dao.listarRecentes();
    }

    public List<Ponto> listarUltimosDois(int id) {
        return dao.listarUltimosDois(id);
    }

    public TipoPonto registrarPonto(String cpf) {
        Pessoa.validarCpf(cpf);
        FuncionarioService fAuxiliar = new FuncionarioService();

        int idFuncionario = fAuxiliar.buscarIdPorCpf(cpf);
        TipoPonto tipoPonto = fAuxiliar.buscarTipoPontoAtual(idFuncionario);

        TipoPonto novoTipo = (tipoPonto == TipoPonto.SAIDA || tipoPonto == null) ? TipoPonto.ENTRADA : TipoPonto.SAIDA;
        dao.registrarPonto(idFuncionario, novoTipo);

        return novoTipo;
    }
}
