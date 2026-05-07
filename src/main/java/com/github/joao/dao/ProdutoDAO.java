package com.github.joao.dao;

import com.github.joao.model.Produto;
import com.github.joao.model.Sabor;
import com.github.joao.util.DatabaseHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ProdutoDAO {

    public List<Produto> listar() {
        String sql = """
                SELECT p.*, s.*, ps.quantidade FROM tbproduto p 
                INNER JOIN tbproduto_sabor ps ON ps.id_produto_PK_FK = p.id_produto_PK
                INNER JOIN tbsabor s ON ps.id_sabor_PK_FK = s.id_sabor_PK
                """;

        List<Map<String, Object>> result = DatabaseHelper.executeQuery(sql);
        List<Produto> produtos = new ArrayList<>();

        for (Map<String, Object> linha : result) {
            Produto p = new Produto();

            p.setId((int) linha.get("id_produto_PK"));
            p.setNome((String) linha.get("nome"));
            p.setPreco(new BigDecimal(linha.get("preco").toString()));
            p.setQuantidade((int) linha.get("quantidade"));

            Sabor s = new Sabor();
            s.setId((int) linha.get("id_sabor_PK"));
            s.setSabor(linha.get("sabor").toString());
            p.setSabor(s);

            produtos.add(p);
        }
        return produtos;
    }

    /**
     * Insere um Produto novo, caso ele não exista
     * @param p Produto
     * @return Chave Primária (PK) do Produto
     */
    public int inserirProduto(Produto p) {
        int id = buscarIdPorNome(p.getNome());
        if (id > 0) return id;

        String sql = "INSERT INTO tbproduto (nome, preco) VALUES (?, ?)";
        DatabaseHelper.executeCommand(sql, p.getNome(), p.getPreco());
        return buscarIdPorNome(p.getNome());
    }

    /**
     * Insere um Sabor novo, caso ele não exista
     * @param s Sabor
     * @return Chave Primária (PK) do Sabor
     */
    public int inserirSabor(Sabor s) {
        int id = buscarIdSabor(s.getSabor());
        if (id > 0) return id;

        String sql = "INSERT INTO tbsabor (sabor) VALUES (?)";
        DatabaseHelper.executeCommand(sql, s.getSabor());
        return buscarIdSabor(s.getSabor());
    }

    /**
     * Insere uma relação Produto-Sabor. Define quantidade ‘default’ como 0
     * @param idProduto id do Produto
     * @param idSabor id do Sabor
     */
    public void inserirRelacao(int idProduto, int idSabor) {
        String sql = "INSERT INTO tbproduto_sabor (id_produto_PK_FK, id_sabor_PK_FK) VALUES (?, ?)";

        DatabaseHelper.executeCommand(sql, idProduto, idSabor);
    }

    /**
     * Incrementa a quantidade do Produto-Sabor no estoque
     * @param idP id do Produto
     * @param idS id do Sabor
     * @param qtd quantidade de novos Produto-Sabor
     */
    public void incrementarQuantidade(int idP, int idS, int qtd) {
        String sql = "UPDATE tbproduto_sabor SET quantidade = quantidade + ? WHERE id_produto_PK_FK = ? AND id_sabor_PK_FK = ?";

        DatabaseHelper.executeCommand(sql, qtd, idP, idS);
    }


    public int buscarIdPorNome(String nome) {
        String sql = "SELECT id_produto_PK FROM tbproduto WHERE nome = ?";

        Object result = DatabaseHelper.executeQueryUniqueValue(sql, nome);
        return result != null ? ((Number) result).intValue() : 0;
    }

    public int buscarIdSabor(String sabor) {
        String sql = "SELECT id_sabor_PK FROM tbsabor WHERE sabor = ?";

        Object result = DatabaseHelper.executeQueryUniqueValue(sql, sabor);
        return result != null ? ((Number) result).intValue() : 0;
    }

    public List<Sabor> buscarSabores(int idProduto) {
        String sql = """
                SELECT s.* FROM tbsabor s
                INNER JOIN tbproduto_sabor ps ON s.id_sabor_PK = ps.id_sabor_PK_FK
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

    /**
     * Verifica se a relação Produto-Sabor existe
     * @param idP id do Produto
     * @param idS id do Sabor
     * @return true se existe
     */
    public boolean relacaoExiste(int idP, int idS) {
        String sql = "SELECT COUNT(*) FROM tbproduto_sabor WHERE id_produto_PK_FK = ? AND id_sabor_PK_FK = ?";

        Object result = DatabaseHelper.executeQueryUniqueValue(sql, idP, idS);
        return result != null && ((Number) result).longValue() > 0;
    }
}