package com.github.joao.view;

import com.github.joao.service.IngredienteService;
import javafx.fxml.FXML;
import com.github.joao.model.Ingrediente;
import com.github.joao.model.UnidadeMedida;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;

public class IngredienteController {

    IngredienteService ingService = new IngredienteService();

    // Tabela de ingredientes
    @FXML
    private TableView<Ingrediente> tableIngredientes;
    @FXML
    private TableColumn<Ingrediente, String> colIngrediente;
    @FXML
    private TableColumn<Ingrediente, UnidadeMedida> colUnidade;
    @FXML
    private TableColumn<Ingrediente, Integer> colQuantidade;

    // Cadastro de ingredientes
    @FXML private TextField txtNome;
    @FXML private Spinner<UnidadeMedida> spinUnidade;
    @FXML private Spinner<BigDecimal> spinQtd;

    @FXML
    public void initialize() {
        colIngrediente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colUnidade.setCellValueFactory(new PropertyValueFactory<>("unidade"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        atualizarDados();
    }

    public void atualizarDados() {
        List<Ingrediente> ingredientes = ingService.listar();

        tableIngredientes.getItems().setAll(ingredientes);
    }
}
