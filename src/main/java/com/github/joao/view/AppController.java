package com.github.joao.view;

import com.github.joao.util.MessageHelper;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;


public class AppController {

    @FXML
    private BorderPane paneApp;

    public void selecionarModulo(ActionEvent e) {
        Button btnClicado = (Button) e.getSource();

        switch (btnClicado.getId()) {
            case "btnVendas" -> trocarModulo("ModuloVendas");
            case "btnClientes" -> trocarModulo("ModuloClientes");
            case "btnFuncionarios" -> trocarModulo("ModuloFuncionarios");
            case "btnProdutos" -> trocarModulo("ModuloProdutos");
        }
    }

    public void trocarModulo(String modulo) {
        try {
            Parent moduloSelecionado = FXMLLoader.load(getClass().getResource("/" + modulo + ".fxml"));

            paneApp.setCenter(moduloSelecionado);

        } catch (IOException e) {
            MessageHelper.mostrarMensagem(Alert.AlertType.ERROR, "Erro", "Erro ao trocar de módulo", e.getMessage());
        }
    }
}
