package com.github.joao;

public class Pessoa {
    private int id;
    private String nome, cpf, telefone, endereco;

    public Pessoa() {
    }

    public Pessoa(int id, String nome, String cpf, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        if (!nome.isBlank() && nome.length() <= 100)
            this.nome = nome;
        else throw new IllegalArgumentException("Nome deve ter menos de 100 caracteres e não pode ser vazio!");
    }

    public void setCpf(String cpf) {
        if (!cpf.isBlank() && cpf.length() == 11)
            this.cpf = cpf;
        else throw new IllegalArgumentException("CPF deve possuir 11 caracteres e não pode estar vazio!");
    }

    public void setTelefone(String telefone) {
        if (!telefone.isBlank() && telefone.length() >= 11 && telefone.length() <= 20)
            this.telefone = telefone;
        else throw new IllegalArgumentException("Telefone deve ter no mínimo 11 e no máximo 20 caracteres e não pode estar vazio!");
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
