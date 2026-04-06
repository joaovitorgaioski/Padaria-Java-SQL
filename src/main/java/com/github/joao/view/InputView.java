package com.github.joao.view;

import com.github.joao.model.Cliente;
import com.github.joao.model.Funcionario;
import com.github.joao.model.Pessoa;
import com.github.joao.model.Produto;

import java.util.Scanner;

// Injeção de Dependência, usamos o mesmo scan passado de App
public class InputView {
    private Scanner scan;

    public InputView(Scanner scan) {
        this.scan = scan;
    }

    private void preencherDadosBase(Pessoa p) {
        System.out.print("Nome: ");
        p.setNome(scan.nextLine());
        System.out.print("CPF: ");
        p.setCpf(scan.nextLine());
        System.out.print("Telefone: ");
        p.setTelefone(scan.nextLine());
        System.out.print("Endereço: ");
        p.setEndereco(scan.nextLine());
    }

    public void preencherDadosCliente(Cliente c) {
        preencherDadosBase(c);

        System.out.println("Cliente filiado?\n[0] - Não\t[1] - Sim");
        c.setFiliacao(Integer.parseInt(scan.nextLine()));
    }

    public void preencherDadosFuncionario(Funcionario f) {
        preencherDadosBase(f);

        System.out.print("Horas de Trabalho: ");
        f.setHorasTrabalho(Integer.parseInt(scan.nextLine()));
        System.out.print("Salário: ");
        f.setSalario(Double.parseDouble(scan.nextLine()));
    }

    public void preencherDadosProduto(Produto p) {
        System.out.print("Nome: ");
        p.setNome(scan.nextLine());
        System.out.print("Sabor: ");
        p.setSabor(scan.nextLine());
        System.out.print("Quantidade: ");
        p.setQuantidade(App.lerInteiro(scan));
    }
}
