package com.github.joao.model;

import java.math.BigDecimal;

public class Ingrediente {
    private int id;
    private String nome;
    private UnidadeMedida unidade;
    private BigDecimal quantidade;

    public Ingrediente() {
    }

    public Ingrediente(int id, String nome, UnidadeMedida unidade, BigDecimal quantidade) {
        this.id = id;
        this.nome = nome;
        this.unidade = unidade;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public UnidadeMedida getUnidade() {
        return unidade;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUnidade(UnidadeMedida unidade) {
        this.unidade = unidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

}
