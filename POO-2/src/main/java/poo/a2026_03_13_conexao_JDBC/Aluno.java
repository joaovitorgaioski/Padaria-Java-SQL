package poo.a2026_03_13_conexao_JDBC;

public class Aluno {
    private int id;
    private String nome;

    public Aluno() {
        this.nome = "Aluno Zacarias";
    }

    public Aluno(String nome) {
        this.nome = nome;
    }

    public Aluno(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "id: " + id + "\tnome: " + nome;
    }
}
