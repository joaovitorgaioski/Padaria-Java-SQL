package com.github.joao.view;

import javafx.fxml.FXML;
import com.github.joao.model.Cliente;
import com.github.joao.service.ClienteService;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ClienteController {

    ClienteService clienteService = new ClienteService();

    @FXML private TableView<Cliente> tableCadastrados;
    @FXML private TableColumn<Cliente, String> colNome;
    @FXML private TableColumn<Cliente, Integer> colFiliacao;
    @FXML private TableColumn<Cliente, String> colCpf;
    @FXML private TableColumn<Cliente, String> colTelefone;
    @FXML private TableColumn<Cliente, String> colEndereco;

    // Metodo initialize() é executado no momento em que o FXMLLoader termina de carregar o .fxml
    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colFiliacao.setCellValueFactory(new PropertyValueFactory<>("filiacao"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));

        atualizarTabela();
    }

    public void atualizarTabela() {
        List<Cliente> clientes = clienteService.listar();

        tableCadastrados.getItems().setAll(clientes);
    }
}
