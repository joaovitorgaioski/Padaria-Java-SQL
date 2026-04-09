package com.github.joao.service;

import com.github.joao.dao.ProdutoDAO;
import com.github.joao.model.Produto;

import java.util.List;

public class ProdutoService {
    private ProdutoDAO dao;

    public ProdutoService() {
        this.dao = new ProdutoDAO();
    }

    public int inserir(Produto p) {
        return dao.inserir(p);
    }

    public List<Produto> listar() {
        return dao.listar();
    }
}
