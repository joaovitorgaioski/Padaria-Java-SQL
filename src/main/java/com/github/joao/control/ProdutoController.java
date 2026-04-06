package com.github.joao.control;

import com.github.joao.dao.ProdutoDAO;
import com.github.joao.model.Produto;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO dao;

    public ProdutoController() {
        this.dao = new ProdutoDAO();
    }

    public int inserir(Produto p) {
        return dao.inserir(p);
    }

    public List<Produto> listar() {
        return dao.listar();
    }
}
