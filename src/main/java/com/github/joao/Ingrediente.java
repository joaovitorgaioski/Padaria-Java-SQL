package com.github.joao;

import java.util.Date;

public class Ingrediente {
    private int id;
    private String nome;
    private Date dataEntrada, dataSaida;

    public Ingrediente() {
    }

    public Ingrediente(int id, String nome, Date dataEntrada) {
        this.id = id;
        this.nome = nome;
        this.dataEntrada = dataEntrada;
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
}
