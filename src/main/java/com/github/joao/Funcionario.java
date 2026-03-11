package com.github.joao;

public class Funcionario extends Pessoa {
    private int horarioTrabalho;
    private double salario;

    public Funcionario() {
    }

    public Funcionario(int id, String nome, String cpf, String telefone, String endereco, int horarioTrabalho, double salario) {
        super(id, nome, cpf, telefone, endereco);
        this.horarioTrabalho = horarioTrabalho;
        this.salario = salario;
    }

    public int getHorarioTrabalho() {
        return horarioTrabalho;
    }

    public void setHorarioTrabalho(int horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
