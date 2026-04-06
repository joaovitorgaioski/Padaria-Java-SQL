package com.github.joao.view;

import com.github.joao.control.ClienteController;
import com.github.joao.control.FuncionarioController;
import com.github.joao.control.ProdutoController;
import com.github.joao.model.Cliente;
import com.github.joao.model.Funcionario;
import com.github.joao.model.Produto;

import java.util.Scanner;

/*
    O Tratamento de Excessões é importante, pois caso ocorra o lançamento de uma em qualquer camada, ela será jogada
    até a camada mais próxima do usuário, que é esta, a View. A lógica é que quando nós fazemos um 'throw new ...' o
    Java para a execução do código e a exceção "sobe" através de cada metodo até chegar em um 'catch' para tratar essa
    exceção, no caso o 'catch' esta aqui nesse código.
 */

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int op = -1;

        ClienteController clienteController = new ClienteController();
        FuncionarioController funcionarioController = new FuncionarioController();
        ProdutoController produtoController = new ProdutoController();
        InputView inputView = new InputView(scan);

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
            op = lerInteiro(scan);
            linha();

            switch (op) {
                case 1, 4, 5:
                    System.out.println("Ainda não implementado!");
                    break;

                case 2:
                    System.out.println("[1] - Cliente\t [2] - Funcionário");
                    op = lerInteiro(scan);

                    try {
                        switch (op) {
                            case 1:
                                Cliente c = new Cliente();
                                inputView.preencherDadosCliente(c);

                                clienteController.cadastrar(c);
                                System.out.println(c.getFiliacao() == 0 ? "Cliente cadastrado com sucesso!" : "Cliente filiado cadastrado com sucesso!");
                                break;

                            case 2:
                                Funcionario f = new Funcionario();
                                inputView.preencherDadosFuncionario(f);

                                funcionarioController.cadastrar(f);
                                System.out.println("Funcionário cadastrado com sucesso!");
                                break;

                            default:
                                op = -1;
                                break;
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro no cadastro: " + e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("Erro na execução: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        Produto p = new Produto();
                        inputView.preencherDadosProduto(p);

                        if (produtoController.inserir(p) > 0)
                            System.out.println("Produto incrementado no estoque com sucesso!");
                        else
                            System.out.println("Produto inserido com sucesso!");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro na inserção de produtos: " + e.getMessage());
                    } catch (RuntimeException e) {
                        System.out.println("Erro na execução: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Deseja realmente sair?\n[0] - Sair\t [1] - Não");
                    op = lerInteiro(scan);
                    if (op == 0) System.out.println("Bye");

                    break;

                default:
                    System.out.println("Escolha uma das opções disponíveis!");
                    break;
            }
            if (op != 0) op = -1;
        }
        scan.close();
    }

    public static int lerInteiro(Scanner scan) {
        try {
            return Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void linha() {
        System.out.println("-----" + "=".repeat(20) + "-----");
    }
}
