package com.github.joao.control;

import com.github.joao.dao.ProdutoDAO;
import com.github.joao.model.Produto;

import java.util.List;

public class ProdutoController {
    private ProdutoDAO dao;

    public ProdutoController() {
        this.dao = new ProdutoDAO();
    }

    public void inserir(Produto p) {
        int linhasAfetadas = dao.inserir(p);

        System.out.println(linhasAfetadas > 0 ? "Produto incrementado no estoque com sucesso!" : "Produto inserido com sucesso!");
    }

    public List<Produto> listar() {
        return dao.listar();
    }
}
