package com.github.joao;

public class Funcionario extends Pessoa {
    private int horasTrabalho;
    private double salario;

    public Funcionario() {
    }

    public Funcionario(int id, String nome, String cpf, String telefone, String endereco, int horasTrabalho, double salario) {
        super(id, nome, cpf, telefone, endereco);
        this.horasTrabalho = horasTrabalho;
        this.salario = salario;
    }

    public int getHorasTrabalho() {
        return horasTrabalho;
    }

    public double getSalario() {
        return salario;
    }

    public void setHorasTrabalho(int horasTrabalho) {
        if (horasTrabalho > 0 && horasTrabalho <= 10)
            this.horasTrabalho = horasTrabalho;
        else
            throw new IllegalArgumentException("O funcionário pode trabalhar no máximo 10 horas diárias e este dado é obrigatório!");
    }

    public void setSalario(double salario) {
        if (salario > 0 && salario < 100000.00)
            this.salario = salario;
        else throw new IllegalArgumentException("Salário deve ser um valor entre R$0 e 100000.00 e não pode ser nulo!");
    }
}
