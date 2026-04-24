package com.github.joao.service;

import com.github.joao.dao.ProdutoDAO;
import com.github.joao.model.Produto;
import com.github.joao.model.Sabor;

import java.util.List;

public class ProdutoService {
    private ProdutoDAO dao;

    public ProdutoService() {
        this.dao = new ProdutoDAO();
    }

//    public int inserir(Produto p) {
//        return dao.inserir(p);
//    }

    /**
     * Lista todos os produtos com todos os sabores que cada produto possui.
     *
     * @return Lista de todos os produtos
     */
    public List<Produto> listar() {
        List<Produto> produtos = dao.listar();

        for (Produto p : produtos) {
            int id = p.getId();
            p.setSabores(buscarSabores(id));
        }

        return produtos;
    }

    public List<Sabor> buscarSabores(int idFuncionario) {
        return dao.buscarSabores(idFuncionario);
    }
}
