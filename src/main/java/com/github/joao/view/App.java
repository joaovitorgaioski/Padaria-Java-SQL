package com.github.joao.view;

import com.github.joao.model.Cliente;
import com.github.joao.model.Funcionario;
import com.github.joao.model.Pessoa;
import com.github.joao.model.Produto;
import com.github.joao.dao.ClienteDAO;
import com.github.joao.dao.FuncionarioDAO;
import com.github.joao.dao.ProdutoDAO;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int op = -1;

        while (op != 0) {
            linha();
            System.out.println("""
                    [1] - Cadastrar pessoa
                    [2] - Inserir produto
                    [3] - Inserir ingrediente
                    [4] - Iniciar pedido
                    [5] - Listar
                    [0] - SAIR""");
            op = Integer.parseInt(scan.nextLine()); // Limpeza de buffer

            switch (op) {
                case 1:
                    System.out.println("----===== Cadastrar Pessoa =====----");
                    System.out.println("Quem você deseja cadastrar?\n(1) - Cliente \t(2) - Funcionário");
                    op = Integer.parseInt(scan.nextLine());

                    switch (op) {
                        case 1:
                            Cliente c = new Cliente();
                            preencherDados(c);

                            System.out.println("Filiado?\n(0) - Não\t(1) - Sim");
                            c.setFiliacao(Integer.parseInt(scan.nextLine()));

                            ClienteDAO cDAO = new ClienteDAO();
                            cDAO.cadastrar(c);
                            break;

                        case 2:
                            Funcionario f = new Funcionario();
                            preencherDados(f);

                            System.out.print("Horas de trabalho diário: ");
                            f.setHorasTrabalho(Integer.parseInt(scan.nextLine()));
                            System.out.print("Salário: ");
                            f.setSalario(Double.parseDouble(scan.nextLine()));

                            FuncionarioDAO fDAO = new FuncionarioDAO();
                            fDAO.cadastrar(f);
                            break;

                        default:
                            System.out.println("Escolha uma opção válida!");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("----===== Inserir Produto =====----");
                    Produto prod = new Produto();

                    try {
                        System.out.print("Nome: ");
                        prod.setNome(scan.nextLine());
                        System.out.print("Sabor: ");
                        prod.setSabor(scan.nextLine());
                        System.out.print("Quantidade: ");
                        prod.setQuantidade(Integer.parseInt(scan.nextLine()));

                        ProdutoDAO prodDAO = new ProdutoDAO();
                        prodDAO.salvar(prod);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro na inserção de valores!" + e);
                    }
                    break;

                default:
                    System.out.println("Escolha uma opção válida!");
                    break;
            }

            if (op != 0) op = -1;
        }
    }

    public static void preencherDados(Pessoa p) {
        try {
            Scanner scan = new Scanner(System.in);

            System.out.print("Nome: ");
            p.setNome(scan.nextLine());
            System.out.print("CPF: ");
            p.setCpf(scan.nextLine());
            System.out.print("Telefone: ");
            p.setTelefone(scan.nextLine());
            System.out.print("Endereço: ");
            p.setEndereco(scan.nextLine());

        } catch (IllegalArgumentException e) {
            System.out.println("Erro na inserção dos dados!" + e);
        }
    }

    public static void linha() {
        System.out.println("-----" + "=".repeat(30) + "-----");
    }
}
