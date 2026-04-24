package com.github.joao.view;

import com.github.joao.service.ProdutoService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import com.github.joao.model.Produto;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoController {

    ProdutoService produtoService = new ProdutoService();

    // Tabela de produtos
    @FXML
    TableView<Produto> tableProdutos;
    @FXML
    TableColumn<Produto, String> colNome, colSabor;
    @FXML
    TableColumn<Produto, BigDecimal> colPreco;
    @FXML
    TableColumn<Produto, Integer> colEstoque;

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colSabor.setCellValueFactory(celula -> {
            String sabor = celula.getValue().formatarSaboresString();
            return new SimpleStringProperty(sabor);
        });

        atualizarTabela();
    }

    public void atualizarTabela() {
        List<Produto> produtos = produtoService.listar();
        tableProdutos.getItems().addAll(produtos);
    }
}
