package com.github.joao;

import java.util.Date;

public class Ingrediente {
    private int id;
    private String nome, unidadeIngrediente;
    private Date dataEntrada, dataSaida;

    public Ingrediente() {
    }

    public Ingrediente(int id, String nome, String unidadeIngrediente, Date dataEntrada, Date dataSaida) {
        this.id = id;
        this.nome = nome;
        this.unidadeIngrediente = unidadeIngrediente;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
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

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getUnidadeIngrediente() {
        return unidadeIngrediente;
    }

    public void setUnidadeIngrediente(String unidadeIngrediente) {
        this.unidadeIngrediente = unidadeIngrediente;
    }
}
