package com.github.joao;

public class Produto {
    private int id;
    private String nome;
    private String sabor;

    public Produto() {}

    public Produto(int id, String nome, String sabor) {
        this.id = id;
        this.nome = nome;
        this.sabor = sabor;
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

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }
}
