package com.github.joao.model;

import java.math.BigDecimal;

public class Produto {
    private int id, quantidade;
    private String nome, sabor;
    private BigDecimal preco;

    public Produto() {
    }

    public Produto(String nome, String sabor, int quantidade, BigDecimal preco) {
        this.nome = nome;
        this.sabor = sabor;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSabor() {
        return sabor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        if (nome.length() <= 50 && !nome.isBlank())
            this.nome = nome;
        else throw new IllegalArgumentException("Nome deve conter menos de 50 caracteres e e não pode ser vazio!");
    }

    public void setSabor(String sabor) {
        if (sabor.length() <= 20 && !sabor.isBlank())
            this.sabor = sabor;
        else
            throw new IllegalArgumentException("Sabor deve conter menos de 20 caracteres e não pode ser vazio!");
    }

    public void setQuantidade(int quantidade) {
        if (quantidade > 0)
            this.quantidade = quantidade;
        else throw new IllegalArgumentException("Coloque uma quantidade adequada e não nula!");
    }

    public void setPreco(BigDecimal preco) {
        if (preco.doubleValue() > 0 && preco.doubleValue() < 10000.00)
            this.preco = preco;
        else throw new IllegalArgumentException("Preço deve estar entre R$ 0 e R$ 10000 !");
    }
}
