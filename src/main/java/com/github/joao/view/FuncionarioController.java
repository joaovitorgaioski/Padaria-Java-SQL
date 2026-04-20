package com.github.joao.view;

import javafx.fxml.FXML;
import com.github.joao.model.Funcionario;
import com.github.joao.service.FuncionarioService;
import com.github.joao.util.MessageHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.List;

public class FuncionarioController {

    FuncionarioService funcionarioService = new FuncionarioService();

    // Tab de histórico
    @FXML
    private TableView<Funcionario> tableCadastrados;
    @FXML
    private TableColumn<Funcionario, String> colNome, colCpf, colTelefone, colEndereco;
    @FXML
    private TableColumn<Funcionario, BigDecimal> colSalario;
    @FXML
    private TableColumn<Funcionario, Integer> colHorasTrabalho;

    // Tab de cadastro
    @FXML
    private TextField txtNome, txtCpf, txtTelefone, txtSalario;
    @FXML
    private TextArea txtEndereco;
    @FXML
    private ComboBox<Integer> comboHorasTrabalho;

    // Metodo initialize() é executado no momento em que o FXMLLoader termina de carregar o .fxml
    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colHorasTrabalho.setCellValueFactory(new PropertyValueFactory<>("horasTrabalho"));

        atualizarTabela();

        comboHorasTrabalho.getItems().addAll(4, 5, 6, 7, 8);
    }

    public void atualizarTabela() {
        List<Funcionario> funcionarios = funcionarioService.listar();

        tableCadastrados.getItems().setAll(funcionarios);
    }


    public void cadastrar(ActionEvent e) {
        try {
            Funcionario f = new Funcionario();

            f.setNome(txtNome.getText());
            f.setCpf(txtCpf.getText());
            f.setTelefone(txtTelefone.getText());
            f.setEndereco(txtEndereco.getText());
            f.setSalario(new BigDecimal(txtSalario.getText()));
            f.setHorasTrabalho(comboHorasTrabalho.getValue());

            funcionarioService.cadastrar(f);
            MessageHelper.mostrarMensagem(Alert.AlertType.INFORMATION, "Sucesso", "Funcionário cadastrado com sucesso!");
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
        txtSalario.clear();
        comboHorasTrabalho.setValue(null);
    }
}
