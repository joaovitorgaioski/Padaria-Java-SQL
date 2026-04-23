package com.github.joao.model;

import java.util.Date;

public class Ponto {
    private int id;
    private Date dataHora;
    private TipoPonto tipo;
    private Funcionario funcionario;

    public Ponto() {
    }

    public int getId() {
        return id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public TipoPonto getTipo() {
        return tipo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public void setTipo(TipoPonto tipo) {
        this.tipo = tipo;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
