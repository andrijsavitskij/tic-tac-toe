package com.example.tictactoe.controller;

import com.example.tictactoe.some.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class MainHBox {

    @FXML
    private Pane GameField;
    @FXML
    private HBox DownFieldHBox;
    @FXML
    private HBox UpFieldHBox;
    @FXML
    private VBox LVBox;
    @FXML
    private VBox RVBox;
    @FXML
    private AnchorPane ancor;
    private static Game game;

    @FXML
    public void initialize() {
        Platform.runLater(()-> {
            game = new Game(GameField);
            game.newGame();
        });

//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
//            RVBox.getChildren().add(fxmlLoader.load());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }
}