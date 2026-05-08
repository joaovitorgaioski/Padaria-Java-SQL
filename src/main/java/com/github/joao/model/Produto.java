package com.github.joao.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Produto {
    private int id, quantidade;
    private String nome;
    private BigDecimal preco;
    private Sabor sabor;
    private List<Ingrediente> receita;

    public Produto() {
    }

    public Produto(String nome, int quantidade, BigDecimal preco, Sabor sabor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.sabor = sabor;
        this.receita = new ArrayList<>();
    }

    public Produto(String nome, int quantidade, BigDecimal preco, Sabor sabor, List<Ingrediente> receita) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.sabor = sabor;
        this.receita = receita;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Sabor getSabor() {
        return sabor;
    }

    public List<Ingrediente> getIngredientes() {
        return receita;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome.length() <= 50 && !nome.isBlank())
            this.nome = nome;
        else throw new IllegalArgumentException("Nome deve conter menos de 50 caracteres e e não pode ser vazio!");
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0)
            this.quantidade = quantidade;
        else throw new IllegalArgumentException("Coloque uma quantidade adequada!");
    }

    public void setPreco(BigDecimal preco) {
        if (preco.doubleValue() > 0 && preco.doubleValue() < 10000.00)
            this.preco = preco;
        else throw new IllegalArgumentException("Preço deve estar entre R$ 0 e R$ 10000 !");
    }

    public void setSabor(Sabor sabor) {
        this.sabor = sabor;
    }

    public void setReceita(List<Ingrediente> receita) {
        this.receita = receita;
    }
}
