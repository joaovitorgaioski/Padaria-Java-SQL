package com.github.joao.view;

import com.github.joao.model.Funcionario;
import com.github.joao.service.FuncionarioService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class FuncionarioController {

    FuncionarioService funcionarioService = new FuncionarioService();

    @FXML private TableView<Funcionario> tableCadastrados;
    @FXML private TableColumn<Funcionario, String> colNome;
    @FXML private TableColumn<Funcionario, String> colCpf;
    @FXML private TableColumn<Funcionario, String> colTelefone;
    @FXML private TableColumn<Funcionario, String> colEndereco;
    @FXML private TableColumn<Funcionario, Double> colSalario;
    @FXML private TableColumn<Funcionario, Integer> colHorasTrabalho;

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
    }

    public void atualizarTabela() {
        List<Funcionario> funcionarios = funcionarioService.listar();

        tableCadastrados.getItems().setAll(funcionarios);
    }
}
