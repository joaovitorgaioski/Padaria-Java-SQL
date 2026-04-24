package com.github.joao.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Produto {
    private int id, quantidade;
    private String nome;
    private BigDecimal preco;
    private List<Sabor> sabores = new ArrayList<>();

    public Produto() {
    }

    public Produto(String nome, int quantidade, BigDecimal preco, Sabor sabor) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.sabores.add(sabor);
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

    public List<Sabor> getSabores() {
        return sabores;
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
        if (quantidade > 0)
            this.quantidade = quantidade;
        else throw new IllegalArgumentException("Coloque uma quantidade adequada e não nula!");
    }

    public void setPreco(BigDecimal preco) {
        if (preco.doubleValue() > 0 && preco.doubleValue() < 10000.00)
            this.preco = preco;
        else throw new IllegalArgumentException("Preço deve estar entre R$ 0 e R$ 10000 !");
    }

    public void setSabores(List<Sabor> sabores) {
        this.sabores = sabores;
    }

    public String formatarSaboresString() {
        if (sabores.isEmpty()) return "Sem sabor";

        return sabores.stream()
                .map(Sabor::getSabor)
                .collect(Collectors.joining(", "));
    }
}
