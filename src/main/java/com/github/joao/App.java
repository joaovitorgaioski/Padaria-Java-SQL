package com.github.joao;

import java.util.List;

public class App {
    public static void main(String[] args) {
        Produto p = new Produto();
        ProdutoDAO pdao = new ProdutoDAO();

        p.setNome("Pão de Queijo");
        p.setSabor("Queijo com Bacon");

        pdao.salvar(p);

        System.out.println("\n------------------------------------");

        System.out.println("Buscando todos os produtos no banco...");

        List<Produto> lista = pdao.listar();

        if (lista.isEmpty()) {
            System.out.println("Nenhum produto encontrado. Verifique sua conexão ou tabela.");
        } else {
            for (Produto pr : lista) {
                System.out.println("ID: " + pr.getId() + " | Nome: " + pr.getNome() + " | Sabor: " + pr.getSabor());
            }
        }
    }
}
