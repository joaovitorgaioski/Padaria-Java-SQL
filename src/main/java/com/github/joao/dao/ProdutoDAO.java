package com.github.joao.dao;

import com.github.joao.model.Produto;
import com.github.joao.model.Sabor;
import com.github.joao.util.DatabaseHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ProdutoDAO {

    // Para implementar o metodo listar, traduzimos de SQL para Objeto

    public List<Produto> listar() {
        String sql = "SELECT * FROM tb_produto";

        List<Map<String, Object>> result = DatabaseHelper.executeQuery(sql);
        List<Produto> produtos = new ArrayList<>();

        for (Map<String, Object> linha : result) {
            Produto p = new Produto();

            p.setId((int) linha.get("id_produto_PK"));
            p.setNome((String) linha.get("nome"));
            p.setPreco(new BigDecimal(linha.get("preco").toString()));
            p.setQuantidade((int) linha.get("quantidade"));

            produtos.add(p);
        }
        return produtos;
    }

    // Para implementar o metodo inserir, traduzimos de Objeto para SQL

//    public int inserir(Produto p) {
//        return 0;
//    }

    public List<Sabor> buscarSabores(int idProduto) {
        String sql = """
                SELECT s.* FROM tb_sabor s
                JOIN tb_produto_sabor ps ON s.id_sabor_PK = ps.id_sabor_PK_FK
                WHERE ps.id_produto_PK_FK = ?
                """;

        List<Map<String, Object>> result = DatabaseHelper.executeQuery(sql, idProduto);
        List<Sabor> sabores = new ArrayList<>();

        for (Map<String, Object> linha : result) {
            Sabor s = new Sabor();

            s.setId((int) linha.get("id_sabor_PK"));
            s.setSabor(linha.get("sabor").toString());

            sabores.add(s);
        }

        return sabores;
    }
}