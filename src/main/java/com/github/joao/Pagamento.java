package com.github.joao;

public class Pagamento {
    private int id;
    private String metodoPagamento;

    public Pagamento() {
    }

    public Pagamento(int id, String metodoPagamento) {
        this.id = id;
        this.metodoPagamento = metodoPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
