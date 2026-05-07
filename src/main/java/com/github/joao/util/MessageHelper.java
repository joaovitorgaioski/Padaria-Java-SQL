package com.github.joao.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class MessageHelper {

    /**
     * Método auxiliar para exibir mensagens dinamicamente
     * @param tipo      Tipo da mensagem
     * @param titulo    Título
     * @param cabecalho Cabecalho
     * @param mensagem  Conteúdo da mensagem
     */
    public static void mostrarMensagem(Alert.AlertType tipo, String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(tipo);

        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void mostrarMensagem(Alert.AlertType tipo, String titulo, String mensagem) {
        mostrarMensagem(tipo, titulo, null, mensagem);
    }

    public static boolean confirmar(String titulo, String cabecalho, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(mensagem);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static String inputMessage(String titulo, String cabecalho, String mensagem) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(titulo);
        dialog.setContentText(mensagem);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    public static String inputMessage(String titulo, String mensagem) {
        return inputMessage(titulo, null, mensagem);
    }
}
