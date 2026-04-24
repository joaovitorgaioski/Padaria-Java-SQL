package com.github.joao.model;

public class Sabor {
    private int id;
    private String sabor;

    public Sabor() {
    }

    public Sabor(int id, String sabor) {
        this.id = id;
        this.sabor = sabor;
    }

    public int getId() {
        return id;
    }

    public String getSabor() {
        return sabor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSabor(String sabor) {
        if (sabor.length() <= 50 && !sabor.isBlank())
            this.sabor = sabor;
        else
            throw new IllegalArgumentException("Sabor deve conter menos de 50 caracteres e não pode ser vazio!");
    }
}
