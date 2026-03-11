package com.github.joao;

import java.util.List;
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
                    (5) - Listar produtos \t\t(0) - SAIR 
                    """);
            op = scan.nextInt();
        }

        switch (op) {
            case 1:
                System.out.println("Quem você deseja cadastrar?\n(1) - Cliente \t(2) - Funcionário");
                op = scan.nextInt();
                break;
        }
    }
}
