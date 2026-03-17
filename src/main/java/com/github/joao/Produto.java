package com.github.joao;

public class Produto {
    private int id, quantidade;
    private String nome, sabor;

    public Produto() {
    }

    public Produto(String sabor, String nome, int quantidade, int id) {
        this.sabor = sabor;
        this.nome = nome;
        this.quantidade = quantidade;
        this.id = id;
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
        if (nome.length() <= 50 && !nome.isBlank())
            this.nome = nome;
        else
            throw new IllegalArgumentException("Nome deve conter menos de 50 caracterese e não pode ser vazio!");
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
