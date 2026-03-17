package com.github.joao;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int op = -1;

        System.out.println("-----==== Sistema para Padaria ====-----");

        while (op != 0) {
            System.out.println("""
                    (1) - Cadastrar pessoa \t\t(2) - Inserir produto
                    (3) - Inserir ingrediente \t(4) - Iniciar pedido
                    (5) - Listar \t\t\t\t(0) - SAIR""");
            op = scan.nextInt();
            scan.nextLine(); // Limpeza do buffer

            switch (op) {
                case 1:
                    System.out.println("Quem você deseja cadastrar?\n(1) - Cliente \t(2) - Funcionário");
                    op = scan.nextInt();
                    scan.nextLine();

                    Pessoa p = criarPessoa();

                    switch (op) {
                        case 1:
                            // instanceof verifica se são tipos compatíveis
                            if (p instanceof Cliente c) {
                                System.out.println("Filiado?\t(0) - Não\t(1) - Sim");
                                c.setFiliacao(scan.nextInt());

                                ClienteDAO cDAO = new ClienteDAO();
                                cDAO.cadastrar(c);
                            }
                            break;

                        case 2:
                            if (p instanceof Funcionario f) {
                                System.out.print("Horas de trabalho diário: ");
                                f.setHorarioTrabalho(scan.nextInt());
                                System.out.print("Salário: ");
                                f.setSalario(scan.nextDouble());

                                FuncionarioDAO fDAO = new FuncionarioDAO();
                                fDAO.cadastrar(f);
                            }
                            break;

                        default:
                            System.out.println("Escolha uma opção válida!");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("-----==== Inserir Produto ====-----");
                    Produto prod = new Produto();

                    try {
                        System.out.print("Nome: ");
                        prod.setNome(scan.nextLine());
                        System.out.print("Sabor: ");
                        prod.setSabor(scan.nextLine());
                        System.out.print("Quantidade: ");
                        prod.setQuantidade(scan.nextInt());

                        ProdutoDAO prodDAO = new ProdutoDAO();
                        prodDAO.salvar(prod);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro na inserção de valores!" + e);
                    }
                    break;

                default:
                    System.out.println("Escolha uma das opções disponíveis!");
                    break;
            }

            if (op != 0) op = -1;
        }
    }

    public static Pessoa criarPessoa() {
        Scanner scan = new Scanner(System.in);
        Pessoa p = new Pessoa();

        System.out.print("Nome: ");
        p.setNome(scan.next());
        System.out.print("CPF: ");
        p.setCpf(scan.next());
        System.out.print("Telefone: ");
        p.setTelefone(scan.next());
        System.out.print("Endereço: ");
        p.setEndereco(scan.next());

        return p;
    }
}
