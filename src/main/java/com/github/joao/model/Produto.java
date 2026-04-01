package com.github.joao.model;

public class Produto {
    private int id, quantidade;
    private String nome, sabor;

    public Produto() {
    }

    public Produto(String nome, String sabor, int quantidade) {
        this.nome = nome;
        this.sabor = sabor;
        this.quantidade = quantidade;
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
        this.quantidade = quantidade;
    }
}
