package com.github.joao;

public class Cliente extends Pessoa {
    private boolean filiacao;

    public Cliente() {
    }

    public Cliente(int id, String nome, String cpf, String telefone, String endereco, boolean filiacao) {
        super(id, nome, cpf, telefone, endereco);
        this.filiacao = filiacao;
    }

    public boolean getFiliacao() {
        return this.filiacao;
    }

    public void setFiliacao(boolean filiacao) {
        this.filiacao = filiacao;
    }
}
