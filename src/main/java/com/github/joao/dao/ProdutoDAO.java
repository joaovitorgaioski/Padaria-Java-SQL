package com.github.joao.dao;

import com.github.joao.model.Produto;
import com.github.joao.util.DatabaseHelper;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ProdutoDAO {

    // Para implementar o metodo listar, traduzimos de SQL para Objeto

    public List<Produto> listar() {
        String sql = "SELECT * FROM tb_produto";

        List<Produto> produtos = new ArrayList<>();
        List<Map<String, Object>> result = DatabaseHelper.executeQuery(sql);

        for (Map<String, Object> linha : result) {
            Produto p = new Produto();

            p.setNome((String) linha.get("nome_produto"));
            p.setSabor((String) linha.get("sabor_produto"));
            p.setQuantidade((int) linha.get("quantidade_produto"));

            produtos.add(p);
        }
        return produtos;
    }

    // Para implementar o metodo inserir, traduzimos de Objeto para SQL

    public int inserir(Produto p) {
        // Primeiro tenta realizar incremento com UPDATE
        String sql = "UPDATE tb_produto SET quantidade_produto = quantidade_produto + ? WHERE nome_produto = ? AND sabor_produto = ?";

        int linhasAfetadas = DatabaseHelper.executeCommand(sql, p.getQuantidade(), p.getNome().toLowerCase(), p.getSabor().toLowerCase());

        // Caso não haja mudanças (nenhuma linha foi alterada no UPDATE), realiza o INSERT
        if (linhasAfetadas == 0) {
            sql = "INSERT INTO tb_produto (nome_produto, sabor_produto, quantidade_produto) VALUES (?, ?, ?)";

            DatabaseHelper.executeCommand(sql, p.getNome().toLowerCase(), p.getSabor().toLowerCase(), p.getQuantidade());
        }

        return linhasAfetadas;
    }
}