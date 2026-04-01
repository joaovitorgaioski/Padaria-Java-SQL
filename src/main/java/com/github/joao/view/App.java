package com.github.joao.view;

import com.github.joao.control.ClienteController;
import com.github.joao.control.FuncionarioController;
import com.github.joao.model.Cliente;
import com.github.joao.model.Funcionario;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int op = -1;

        ClienteController clienteController = new ClienteController();
        FuncionarioController funcionarioController = new FuncionarioController();
        PessoaView pessoaView = new PessoaView(scan);

        while (op != 0) {
            linha();
            System.out.print("""
                    [1] - Iniciar pedido
                    [2] - Cadastrar pessoa
                    [3] - Inserir produto
                    [4] - Inserir ingrediente
                    [5] - Listar dados
                    [0] - SAIR
                    Escolha uma opção:""");
            op = Integer.parseInt(scan.nextLine());
            linha();

            switch (op) {
                case 2:
                    System.out.println("[1] - Cliente\t [2] - Funcionário");
                    op = Integer.parseInt(scan.nextLine());

                    try {
                        switch (op) {
                            case 1:
                                Cliente c = new Cliente();
                                pessoaView.preencherDadosCliente(c);

                                if (clienteController.cadastrar(c) == -1) break;

                                System.out.println(c.getFiliacao() == 0 ? "Cliente cadastrado com sucesso!" : "Cliente filiado cadastrado com sucesso!");
                                break;

                            case 2:
                                Funcionario f = new Funcionario();
                                pessoaView.preencherDadosFuncionario(f);

                                if (funcionarioController.cadastrar(f) == -1) break;

                                System.out.println("Funcionário cadastrado com sucesso!");
                                break;

                            default:
                                break;
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro no cadastro!" + e);
                    }
                    break;

                default:
                    System.out.println("Escolha uma das opções disponíveis!");
                    if (op != 0) op = -1;
                    break;
            }
        }

        scan.close();
    }

    public static void linha() {
        System.out.println("-----" + "=".repeat(20) + "-----");
    }
}
