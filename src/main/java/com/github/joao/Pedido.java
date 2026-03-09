package com.github.joao;

import java.util.Date;

public class Pedido {
    private int id;
    private Date dataHora;
    double contaTotal;

    public Pedido() {
    }

    public Pedido(int id, Date dataHora, double contaTotal) {
        this.id = id;
        this.dataHora = dataHora;
        this.contaTotal = contaTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public double getContaTotal() {
        return contaTotal;
    }

    public void setContaTotal(double contaTotal) {
        this.contaTotal = contaTotal;
    }
}
