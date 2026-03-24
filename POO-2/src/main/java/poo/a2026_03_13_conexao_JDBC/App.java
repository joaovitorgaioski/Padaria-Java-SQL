package poo.a2026_03_13_conexao_JDBC;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AlunoDAO dao = new AlunoDAO();

        int op = -1;

        while (op != 0) {
            try {
                System.out.print("""
                        ------==============================------
                        [1] - Inserir aluno
                        [2] - Listar alunos
                        [3] - Atualizar aluno
                        [4] - Excluir aluno
                        [5] - Buscar aluno por id
                        [6] - Buscar aluno por nome
                        [0] - SAIR
                        Escolha uma opção: """);
                op = Integer.parseInt(scan.nextLine()); // Limpa o buffer na hora, muito bom
                System.out.println("------==============================------");

                switch (op) {
                    case 1:
                        System.out.print("Digite o nome do aluno: ");
                        String nome = scan.nextLine();

                        Aluno aluno = new Aluno(nome);
                        dao.inserir(aluno);

                        System.out.println("Aluno inserido com sucesso!");
                        break;

                    case 2:
                        List<Aluno> alunos = dao.listar();

                        if (alunos.isEmpty())
                            System.out.println("Nenhum aluno encontrado!");
                        else alunos.forEach(a -> {
                            System.out.println(a.getId() + "\t\t" + a.getNome());
                        });
                        break;

                    case 3:
                        System.out.print("Digite o id do aluno que deseja atualizar: ");
                        int idAtt = Integer.parseInt(scan.nextLine());

                        Aluno alunoAtualizar = dao.buscarPorId(idAtt);

                        if (alunoAtualizar == null)
                            System.out.println("Aluno não encontrado!");
                        else {
                            System.out.print("Digite um novo nome: ");
                            String novoNome = scan.nextLine();

                            alunoAtualizar.setNome(novoNome);
                            dao.atualizar(alunoAtualizar);

                            System.out.println("Aluno atualizado com sucesso!");
                        }
                        break;

                    case 4:
                        System.out.print("Digite o id do aluno que deseja excluir: ");
                        int idExcluir = Integer.parseInt(scan.nextLine());

                        Aluno alunoExcluir = dao.buscarPorId(idExcluir);

                        if (alunoExcluir == null)
                            System.out.println("Aluno não encontrado!");
                        else {
                            dao.excluir(alunoExcluir);
                            System.out.println("Aluno excluido com sucesso!");
                        }

                        break;

                    case 5:
                        System.out.println("Digite o id do aluno");
                        int idBusca = Integer.parseInt(scan.nextLine());

                        Aluno alunoEncontrado = dao.buscarPorId(idBusca);

                        if (alunoEncontrado == null)
                            System.out.println("Aluno não encontrado!");
                        else {
                            System.out.println(alunoEncontrado.getId() + "\t" + alunoEncontrado.getNome());
                        }
                        break;

                    case 6:
                        System.out.println("Digite o nome do aluno");
                        String buscaPorNome = scan.nextLine();

                        Aluno alunoEncontradoNome = dao.buscarPorNome(buscaPorNome);

                        if (alunoEncontradoNome == null)
                            System.out.println("Aluno não encontrado!");
                        else {
                            System.out.println(alunoEncontradoNome.getId() + "\t" + alunoEncontradoNome.getNome());
                        }
                        break;

                    case 0:
                        System.out.println("Deseja realmente sair? (s) ou (n)");
                        String sair = scan.nextLine();

                        if (sair.equals("s")) System.out.println("Bye");
                        else op = -1;

                        break;

                    default:
                        System.out.println("Escolha uma opção válida");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite um valor válido: [1] a [6] !");
            }
        }
    }
}
