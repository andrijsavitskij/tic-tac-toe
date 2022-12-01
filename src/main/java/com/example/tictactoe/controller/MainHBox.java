package com.example.tictactoe.controller;

import com.example.tictactoe.HelloApplication;
import com.example.tictactoe.some.LogSinInOut;
import com.example.tictactoe.some.gameStaff.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;


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

        LogSinInOut.setPane(RVBox);
        LogSinInOut.start();
    }

}