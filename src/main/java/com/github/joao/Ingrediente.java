package com.github.joao;

import java.util.Date;

public class Ingrediente {
    private int id;
    private String nome, unidadeIngrediente;
    private double quantidade;

    public Ingrediente() {
    }

    public Ingrediente(int id, String nome, String unidadeIngrediente, Double quantidade) {
        this.id = id;
        this.nome = nome;
        this.unidadeIngrediente = unidadeIngrediente;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeIngrediente() {
        return unidadeIngrediente;
    }

    public void setUnidadeIngrediente(String unidadeIngrediente) {
        this.unidadeIngrediente = unidadeIngrediente;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
}
