package com.github.joao.dao;

import com.github.joao.model.Funcionario;
import com.github.joao.model.Ponto;
import com.github.joao.model.TipoPonto;
import com.github.joao.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PontoDAO {

    public void registrarPonto(int id, TipoPonto tipo) {
        String sql = """
                INSERT INTO tb_ponto (data_hora, tipo, id_funcionario_FK) VALUES
                (NOW(), ?, ?)
                """;

        DatabaseHelper.executeCommand(sql, tipo.name(), id);
    }

    /**
     * Consulta os 20 pontos mais recentes com o nome de cada funcionário
     *
     * @return lista de 20 pontos mais recentes
     */
    public List<Ponto> listarRecentes() {
        String sql = """
                SELECT ponto.*, pessoa.nome FROM tb_ponto ponto
                JOIN tb_funcionario funcionario ON ponto.id_funcionario_FK = funcionario.id_pessoa_PK_FK
                JOIN tb_pessoa pessoa ON funcionario.id_pessoa_PK_FK = pessoa.id_pessoa_PK
                ORDER BY ponto.data_hora DESC LIMIT 20
                """;

        List<Map<String, Object>> result = DatabaseHelper.executeQuery(sql);
        List<Ponto> pontosRecentes = new ArrayList<>();

        for (Map<String, Object> linha : result) {
            Ponto p = new Ponto();

            p.setId((int) linha.get("id_ponto_PK"));
            p.setDataHora((Date) linha.get("data_hora"));
            p.setTipo(TipoPonto.valueOf(linha.get("tipo").toString()));

            Funcionario f = new Funcionario((int) linha.get("id_funcionario_FK"), linha.get("nome").toString());
            p.setFuncionario(f);

            pontosRecentes.add(p);
        }

        return pontosRecentes;
    }

    public List<Ponto> listarUltimosDois(int id) {
        String sql = "SELECT data_hora, tipo FROM tb_ponto WHERE id_funcionario_FK = ? ORDER BY data_hora DESC LIMIT 2";

        List<Ponto> ultimosDois = new ArrayList<>();
        List<Map<String, Object>> result = DatabaseHelper.executeQuery(sql, id);

        for (Map<String, Object> linha : result) {
            Ponto p = new Ponto();

            p.setDataHora((Date) linha.get("data_hora"));
            p.setTipo(TipoPonto.valueOf(linha.get("tipo").toString()));

            ultimosDois.add(p);
        }

        return ultimosDois;
    }
}
