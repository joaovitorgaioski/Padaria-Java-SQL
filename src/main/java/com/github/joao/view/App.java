package com.github.joao.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/App.fxml"));

        primaryStage.setTitle("Padaria");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    static void main(String[] args) {
        launch(args);
    }
}