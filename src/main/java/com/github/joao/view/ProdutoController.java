package com.github.joao.view;

import com.github.joao.model.Ingrediente;
import com.github.joao.model.Sabor;
import com.github.joao.model.UnidadeMedida;
import com.github.joao.service.ProdutoService;
import com.github.joao.util.DuplicatedProductException;
import com.github.joao.util.MessageHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.github.joao.model.Produto;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.textfield.TextFields;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoController {

    ProdutoService produtoService = new ProdutoService();

    // Tabela de produtos
    @FXML
    private TableView<Produto> tableProdutos;
    @FXML
    private TableColumn<Produto, String> colNome, colSabor;
    @FXML
    private TableColumn<Produto, BigDecimal> colPreco;
    @FXML
    private TableColumn<Produto, Integer> colEstoque;

    // Adicionar produtos
    @FXML
    private TextField txtNome, txtSabor, txtPreco;
    @FXML
    private Spinner<Integer> spinQtd;

    // Tabela de ingredientes
    @FXML
    private TableView<Ingrediente> tableIngrediente;
    @FXML
    private TableColumn<Ingrediente, String> colIngrediente;
    @FXML
    private TableColumn<Ingrediente, UnidadeMedida> colUnidade;
    @FXML
    private TableColumn<Ingrediente, Integer> colQuantidade;

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colSabor.setCellValueFactory(celula -> new SimpleStringProperty(celula.getValue().getSabor().getSabor()));

        colIngrediente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colUnidade.setCellValueFactory(new PropertyValueFactory<>("unidade"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        atualizarDados();

        spinQtd.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
    }

    public void atualizarDados() {
        produtoService.atualizarCache();

        List<Produto> produtos = produtoService.getListaCache();
        tableProdutos.getItems().setAll(produtos);

        TextFields.bindAutoCompletion(txtNome, produtoService.getNomesCached());
        TextFields.bindAutoCompletion(txtSabor, produtoService.getSaboresCached());
    }

    public void adicionarProduto(ActionEvent e) {
        try {
            Produto p = new Produto();
            p.setNome(txtNome.getText().trim());
            p.setPreco(new BigDecimal(txtPreco.getText()));
            p.setQuantidade(spinQtd.getValue());
            Sabor s = new Sabor(txtSabor.getText().trim());

            produtoService.inserirTudo(p, s);

            MessageHelper.mostrarMensagem(Alert.AlertType.INFORMATION, "Sucesso", "Produto Salvo", "Item registrado com sucesso!");

        } catch (DuplicatedProductException ex) {
            boolean ok = MessageHelper.confirmar("Atenção", ex.getMessage(), "Deseja aumentar o estoque deste produto?");

            if (ok) {
                String qtd = MessageHelper.inputMessage(
                        "Incrementar Estoque",
                        "Insira quanto deseja incrementar no estoque de "
                                + ex.getProduto().getNome() + ", sabor " + ex.getSabor().getSabor()
                );

                if (qtd != null && !qtd.isBlank()) {
                    try {
                        produtoService.incrementarEstoque(ex.getProduto().getId(), ex.getSabor().getId(), Integer.parseInt(qtd));
                        MessageHelper.mostrarMensagem(Alert.AlertType.INFORMATION, "Sucesso", "Produto Salvo", "Quantidade incrementada com sucesso!");
                    } catch (NumberFormatException nex) {
                        MessageHelper.mostrarMensagem(Alert.AlertType.ERROR, "Erro", "Por favor, insira apenas números inteiros.");
                    }
                }

            }
        } catch (IllegalArgumentException ex) {
            MessageHelper.mostrarMensagem(Alert.AlertType.WARNING, "Atenção", "Erro na inserção de valores", ex.getMessage());
        } catch (RuntimeException ex) {
            MessageHelper.mostrarMensagem(Alert.AlertType.ERROR, "Erro", "Erro na interação com o banco", ex.getMessage());
        } finally {
            produtoService.atualizarCache();
            atualizarDados();
            limparFormulario();
        }
    }

    public void cancelar(ActionEvent e) {

    }

    public void limparFormulario() {
        txtNome.clear();
        txtPreco.clear();
        txtSabor.clear();
        spinQtd.getValueFactory().setValue(0);
    }
}
