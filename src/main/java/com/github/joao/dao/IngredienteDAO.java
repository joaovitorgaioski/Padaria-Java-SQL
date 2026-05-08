package com.github.joao.dao;

import com.github.joao.model.Ingrediente;
import com.github.joao.model.UnidadeMedida;
import com.github.joao.util.DatabaseHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IngredienteDAO {
    /**
     * Lista todos os Ingredientes no estoque, desconsiderando os Produtos que os usam
     *
     * @return Lista de todos os ingredientes
     */
    public List<Ingrediente> listar() {
        String sql = "SELECT * FROM tbingrediente";

        List<Map<String, Object>> result = DatabaseHelper.executeQuery(sql);
        List<Ingrediente> ingredientes = new ArrayList<>();

        for (Map<String, Object> linha : result) {
            Ingrediente i = new Ingrediente();

            i.setId((int) linha.get("id_ingrediente_PK"));
            i.setNome(linha.get("nome").toString());
            i.setQuantidade(new BigDecimal(linha.get("quantidade_ingrediente").toString()));
            i.setUnidade(UnidadeMedida.valueOf(linha.get("unidade_medida").toString()));

            ingredientes.add(i);
        }
        return ingredientes;
    }

    /**
     * Lista todos os Ingredientes de um determinado Produto com a quantidade que
     * esse Produto usa de cada Ingrediente
     *
     * @param idProduto id do Produto
     * @param idSabor   id do Sabor do Produto
     * @return Lista de todos os ingredientes associados a esse produto
     */
    public List<Ingrediente> listarPorProduto(int idProduto, int idSabor) {
        String sql = """
                SELECT i.nome, i.unidade_medida, r.quantidade_receita, r.id_ingrediente_PK_FK
                FROM tbingrediente i
                INNER JOIN tbreceita r ON r.id_ingrediente_PK_FK = i.id_ingrediente_PK
                WHERE id_produto_PK_FK = ? AND id_sabor_PK_FK = ?
                """;

        List<Map<String, Object>> result = DatabaseHelper.executeQuery(sql, idProduto, idSabor);
        List<Ingrediente> ingredientes = new ArrayList<>();

        for (Map<String, Object> linha : result) {
            Ingrediente i = new Ingrediente();

            i.setId((int) linha.get("id_ingrediente_PK_FK"));
            i.setNome(linha.get("nome").toString());
            i.setQuantidade(new BigDecimal(linha.get("quantidade_receita").toString()));
            i.setUnidade(UnidadeMedida.valueOf(linha.get("unidade_medida").toString()));

            ingredientes.add(i);
        }
        return ingredientes;
    }
}
