package poo.app_list_set_map_comp_stream;

import java.util.*;

public class App {
    public static void main(String[] args) {
        // List -> jogos
        List<Jogo> jogos = new ArrayList<>();
        jogos.add(new Jogo("Rocket League", "Competitivo", 40));
        jogos.add(new Jogo("Minecraft", "Mundo Aberto", 120));
        jogos.add(new Jogo("Stardew Valley", "RPG", 20));
        jogos.add(new Jogo("Call of Duty®: Black Ops II", "Competitivo", 329.90));
        jogos.add(new Jogo("Cyberpunk 2077", "Mundo Aberto", 199.90));
        jogos.add(new Jogo("FIFA 23", "Esportes", 299.00));

        // Set -> categorias
        Set<String> categorias = new HashSet<>();
        for (Jogo j : jogos) {
            categorias.add(j.getCategoria());
        }
        System.out.println("Categorias existentes: " + categorias);

        // Map -> estoque
        Map<Integer, Jogo> estoque = new HashMap<>();
        for (int i = 0; i < jogos.size(); i++) {
            estoque.put(i, jogos.get(i));
        }
        System.out.println("\nEstoque:");
        estoque.forEach((c, v) -> System.out.println((c + 1) + ": " + v.getNome() + "\t" + v.getCategoria() + "\tR$" + v.getPreco()));

        // Ordenar por preço
        System.out.println("\nOrdenado por preço:");
        jogos.sort(Comparator.comparing(Jogo::getPreco));
        jogos.forEach(System.out::println);

        // Filtrar acima de R$100
        System.out.println("\nJogos acima de R$100:");
        List<Jogo> jogosAcimaDe100 = jogos.stream().filter(j -> j.getPreco() >= 100).toList();
        jogosAcimaDe100.forEach(System.out::println);
    }
}
