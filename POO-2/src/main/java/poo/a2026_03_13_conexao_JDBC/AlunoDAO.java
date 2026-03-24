package poo.a2026_03_13_conexao_JDBC;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    /*
    Sempre seguimos o mesmo padrão:
        Connection
        Statement -> createStatement (consulta)
        PreparedStatement -> prepareStatement (inserção)
        ResultSet (retorno de dados)
     */

    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO tb_aluno (nome) VALUES (?)";

        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setString(1, aluno.getNome());

            // Execução do update
            stm.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar no banco!" + e);
        }
    }

    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM tb_aluno";

        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                alunos.add(new Aluno(rs.getInt("id"), rs.getString("nome")));
            }

            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter valores do banco!" + e);
        }

        return alunos;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE tb_aluno SET nome = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, aluno.getNome());
            stm.setInt(2, aluno.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao tentar atualizar o banco!" + e);
        }
    }

    public void excluir(Aluno aluno) {
        String sql = "DELETE FROM tb_aluno WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, aluno.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao tentar excluir do banco!" + e);
        }
    }

    public Aluno buscarPorId(int id) {
        String sql = "SELECT * FROM tb_aluno WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setInt(1, id);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));

                    return aluno;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar por id no banco!" + e);
        }
        return null;
    }

    public Aluno buscarPorNome(String nome) {
        String sql = "SELECT * FROM tb_aluno WHERE nome = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql)) {

            stm.setString(1, nome);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno();

                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));

                    return aluno;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar por id no banco!" + e);
        }
        return null;
    }
}

















