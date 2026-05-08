package com.github.joao.service;

import com.github.joao.dao.IngredienteDAO;
import com.github.joao.model.Ingrediente;
import com.github.joao.model.Produto;
import com.github.joao.model.Sabor;

import java.util.List;

public class IngredienteService {
    private IngredienteDAO dao;

    public IngredienteService() {
        this.dao = new IngredienteDAO();
    }

    public List<Ingrediente> listar() {
        return dao.listar();
    }

    public List<Ingrediente> listarPorProduto(Produto p, Sabor s) {
        return dao.listarPorProduto(p.getId(), s.getId());
    }
}
