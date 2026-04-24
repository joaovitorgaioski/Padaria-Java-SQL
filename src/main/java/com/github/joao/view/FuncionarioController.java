package com.github.joao.view;

import javafx.fxml.FXML;
import javafx.beans.property.SimpleStringProperty;
import com.github.joao.model.Ponto;
import com.github.joao.model.TipoPonto;
import com.github.joao.service.PontoService;
import com.github.joao.model.Funcionario;
import com.github.joao.service.FuncionarioService;
import com.github.joao.util.MessageHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class FuncionarioController {

    FuncionarioService funcionarioService = new FuncionarioService();
    PontoService pontoService = new PontoService();

    // Tab de pontos
    @FXML
    private TableView<Ponto> tablePontos;
    @FXML
    TableColumn<Ponto, String> colFuncionario;
    @FXML
    private TableColumn<Ponto, Date> colDataHora;
    @FXML
    private TableColumn<Ponto, TipoPonto> colTipoPonto;
    @FXML
    private Button btnRegistrarPonto;
    @FXML
    private TextField txtCpfPonto;

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
        colDataHora.setCellValueFactory(new PropertyValueFactory<>("dataHora"));
        colTipoPonto.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        // Ao invés de só buscar um 'getNome', ele é especializado para obter o nome do funcionário de maneira inteligente
        colFuncionario.setCellValueFactory(celula -> {
            String nome = celula.getValue().getFuncionario().getNome();
            return new SimpleStringProperty(nome);
        });

        atualizarPontos();

        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colHorasTrabalho.setCellValueFactory(new PropertyValueFactory<>("horasTrabalho"));

        atualizarTabela();

        comboHorasTrabalho.getItems().addAll(4, 5, 6, 7, 8);
        configurarPopOverPontos();
    }

    public void atualizarTabela() {
        List<Funcionario> funcionarios = funcionarioService.listar();
        tableCadastrados.getItems().setAll(funcionarios);
    }

    public void atualizarPontos() {
        List<Ponto> pontos = pontoService.listarRecentes();
        tablePontos.getItems().setAll(pontos);
    }

    private void configurarPopOverPontos() {
        tablePontos.setRowFactory(tv -> {
            TableRow<Ponto> row = new TableRow<>();
            PopOver popOver = new PopOver();

            row.setOnMouseEntered(event -> {
                if (!row.isEmpty()) {
                    Funcionario f = row.getItem().getFuncionario();

                    VBox layout = new VBox(10);
                    Label titulo = new Label("Pontos recentes de " + f.getNome());
                    layout.getChildren().add(titulo);

                    List<Ponto> ultimosDois = pontoService.listarUltimosDois(f.getId());

                    if (ultimosDois.isEmpty()) {
                        layout.getChildren().add(new Label("Nenhum registro encontrado."));
                    } else {
                        for (Ponto p : ultimosDois) {
                            String texto = String.format("%s em %s", p.getTipo(), p.getDataHora());
                            layout.getChildren().add(new Label(texto));
                        }
                    }

                    popOver.setContentNode(layout);
                    popOver.setArrowLocation(PopOver.ArrowLocation.LEFT_CENTER);
                    popOver.setCornerRadius(10);
                    popOver.setTitle("Pontos Recentes");

                    popOver.show(row);
                }
            });
            row.setOnMouseExited(event -> {
                popOver.hide();
            });
            return row;
        });
    }

    public void registrarPonto(ActionEvent e) {
        try {
            TipoPonto tipoRegistrado = pontoService.registrarPonto(txtCpfPonto.getText());
            MessageHelper.mostrarMensagem(Alert.AlertType.INFORMATION, "Sucesso", tipoRegistrado.name() + " registrada com sucesso!");
            atualizarPontos();
            txtCpfPonto.clear();

        } catch (IllegalArgumentException ex) {
            MessageHelper.mostrarMensagem(Alert.AlertType.WARNING, "Atenção", "Erro na inserção de valores", ex.getMessage());
        } catch (RuntimeException ex) {
            MessageHelper.mostrarMensagem(Alert.AlertType.ERROR, "Erro", "Erro na interação com o banco", ex.getMessage());
        }
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
