package com.github.joao.model;

import java.math.BigDecimal;

public class Funcionario extends Pessoa {
    private int horasTrabalho;
    private BigDecimal salario;

    public Funcionario() {
    }

    public Funcionario(int id, String nome) {
        super(id, nome);
    }

    public Funcionario(int id, String nome, String cpf, String telefone, String endereco, int horasTrabalho, BigDecimal salario) {
        super(id, nome, cpf, telefone, endereco);
        this.horasTrabalho = horasTrabalho;
        this.salario = salario;
    }

    public int getHorasTrabalho() {
        return horasTrabalho;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setHorasTrabalho(int horasTrabalho) {
        if (horasTrabalho > 0 && horasTrabalho <= 10)
            this.horasTrabalho = horasTrabalho;
        else
            throw new IllegalArgumentException("O funcionário pode trabalhar no máximo 10 horas diárias e este dado é obrigatório!");
    }

    public void setSalario(BigDecimal salario) {
        if (salario.doubleValue() > 0 && salario.doubleValue() < 100000.00)
            this.salario = salario;
        else throw new IllegalArgumentException("Salário deve ser um valor entre R$0 e 100000.00 e não pode ser nulo!");
    }
}