package com.github.joao.service;

import com.github.joao.dao.ProdutoDAO;
import com.github.joao.model.Produto;
import com.github.joao.model.Sabor;
import com.github.joao.util.DuplicatedProductException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoService {
    private ProdutoDAO dao;

    private List<Produto> listaCache = new ArrayList<>();

    public ProdutoService() {
        this.dao = new ProdutoDAO();
    }

    /**
     * Insere um Produto e Sabor, e cria uma referência entre ambos. Caso o Produto ou o Sabor não existam, são criados
     * e inseridos antes do prosseguimento
     *
     * @param p Produto
     * @param s Sabor
     */
    public void inserirTudo(Produto p, Sabor s) {
        int idProduto = dao.inserirProduto(p);
        int idSabor = dao.inserirSabor(s);

        if (dao.relacaoExiste(idProduto, idSabor)) {
            p.setId(idProduto);
            s.setId(idSabor);
            throw new DuplicatedProductException("Este Produto com este Sabor já existe!", p, s);
        } else {
            dao.inserirRelacao(idProduto, idSabor);
            incrementarEstoque(idProduto, idSabor, p.getQuantidade());
            atualizarCache();
        }
    }

    public void incrementarEstoque(int idProduto, int idSabor, int qtd) {
        System.out.println(idProduto + "\t" + idSabor + "\t" + qtd);
        dao.incrementarQuantidade(idProduto, idSabor, qtd);
    }

    public List<Produto> listar() {
        return dao.listar();
    }

    public List<Sabor> buscarSabores(int idProduto) {
        return dao.buscarSabores(idProduto);
    }

    public void atualizarCache() {
        this.listaCache = listar();
    }

    public List<Produto> getListaCache() {
        if (listaCache.isEmpty()) atualizarCache();

        return listaCache;
    }

    public Set<String> getNomesCached() {
        return getListaCache().stream().map(Produto::getNome).collect(Collectors.toSet());
    }

    public Set<String> getSaboresCached() {
        return getListaCache().stream()
                .map(p -> p.getSabor().getSabor())
                .collect(Collectors.toSet());
    }
}