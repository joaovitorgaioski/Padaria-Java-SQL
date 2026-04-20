package com.github.joao.util;

import javafx.scene.control.Alert;

public class MessageHelper {

    /**
     * Método auxiliar para exibir mensagens dinamicamente
     *
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
}
