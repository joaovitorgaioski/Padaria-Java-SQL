package com.github.joao;

public class Cliente extends Pessoa {
    private int filiacao;

    public Cliente() {
    }

    public Cliente(int id, String nome, String cpf, String telefone, String endereco, int filiacao) {
        super(id, nome, cpf, telefone, endereco);
        this.filiacao = filiacao;
    }

    public int getFiliacao() {
        return this.filiacao;
    }

    public void setFiliacao(int filiacao) {
        this.filiacao = filiacao;
    }
}
