package com.github.joao.view;

import com.github.joao.util.MessageHelper;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.github.joao.model.Cliente;
import com.github.joao.service.ClienteService;

import java.util.List;

public class ClienteController {

    ClienteService clienteService = new ClienteService();

    // Tab de histórico
    @FXML
    private TableView<Cliente> tableCadastrados;
    @FXML
    private TableColumn<Cliente, String> colNome, colCpf, colTelefone, colEndereco;
    @FXML
    private TableColumn<Cliente, Integer> colFiliacao;

    // Tab de cadastro
    @FXML
    private TextField txtNome, txtCpf, txtTelefone;
    @FXML
    private TextArea txtEndereco;
    @FXML
    private ComboBox<String> comboFiliacao;

    // Metodo initialize() é executado no momento em que o FXMLLoader termina de carregar o .fxml
    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colFiliacao.setCellValueFactory(new PropertyValueFactory<>("filiacao"));

        atualizarTabela();

        comboFiliacao.getItems().addAll("Filiado", "Não Filiado");
    }

    public void atualizarTabela() {
        List<Cliente> clientes = clienteService.listar();

        tableCadastrados.getItems().setAll(clientes);
    }

    public void cadastrar(ActionEvent e) {
        try {
            Cliente c = new Cliente();

            c.setNome(txtNome.getText());
            c.setCpf(txtCpf.getText());
            c.setTelefone(txtTelefone.getText());
            c.setEndereco(txtEndereco.getText());
            c.setFiliacao(comboFiliacao.getValue().equals("Filiado") ? 1 : 0);

            clienteService.cadastrar(c);
            MessageHelper.mostrarMensagem(Alert.AlertType.INFORMATION, "Sucesso", "Cliente cadastrado com sucesso!");
            atualizarTabela();
            limparFormulario();

        } catch (IllegalArgumentException ex) {
            MessageHelper.mostrarMensagem(Alert.AlertType.WARNING, "Atenção", "Erro na inserção de valores", ex.getMessage());
        } catch (RuntimeException ex) {
            MessageHelper.mostrarMensagem(Alert.AlertType.ERROR, "Erro", "Erro na interação com o banco", ex.getMessage());
        }
    }

    public void handleLimparFormulario(ActionEvent e) {
        limparFormulario();
    }

    public void limparFormulario() {
        txtNome.clear();
        txtCpf.clear();
        txtTelefone.clear();
        txtEndereco.clear();
        comboFiliacao.setValue(null);
    }
}
