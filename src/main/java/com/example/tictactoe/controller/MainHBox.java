package com.example.tictactoe.controller;

import com.example.tictactoe.HelloApplication;
import com.example.tictactoe.Settings;
import com.example.tictactoe.some.LogSinInOut;
import com.example.tictactoe.some.gameStaff.Game;
import com.example.tictactoe.some.newGameStaff.GameController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.example.tictactoe.Settings.TEST_MOD;

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
    private HBox mainHBox;
    private static GameController game;


    @FXML
    public void initialize() {
        Platform.runLater(this::resize);
        Platform.runLater(() -> {
            game = new GameController(GameField, Settings.gameColumn, Settings.gameRows, 3); // FIXME: 02.12.2022 только квадратные матрицы((
            game.newGame();
        });
        LogSinInOut.setPane(RVBox);
        LogSinInOut.start();
        try {
            DownFieldHBox.getChildren().add(new FXMLLoader(HelloApplication.class.getResource("gameStat-view.fxml")).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Platform.runLater(() -> {
            mainHBox.widthProperty().addListener((obs, oldVal, newVal) -> {
                resize();
                game.resize();
            });
            mainHBox.heightProperty().addListener((obs, oldVal, newVal) -> {
                resize();
                game.resize();
            });
        });
        Platform.runLater(() -> {
            Button b = new Button();
            b.setText("new game");
            LVBox.getChildren().add(b);
            b.setOnAction(event -> {
                game.newGame();
            });
        });

        GameField.setStyle(Settings.Color.backGroundMain);
        GameField.getParent().setStyle(Settings.Color.backGroundSecond);
        RVBox.setStyle(Settings.Color.backGroundMain);
        LVBox.setStyle(Settings.Color.backGroundMain);
        UpFieldHBox.setStyle(Settings.Color.backGroundMain);
        DownFieldHBox.setStyle(Settings.Color.backGroundMain);


        /* DEBUG */
        if (TEST_MOD) {
            GameField.setStyle("-fx-background-color: GREEN;");
            GameField.getParent().setStyle("-fx-background-color: RED;");
            RVBox.setStyle("-fx-background-color: GREY;");
            LVBox.setStyle("-fx-background-color: GREY;");
            UpFieldHBox.setStyle("-fx-background-color: YELLOW;");
            DownFieldHBox.setStyle("-fx-background-color: YELLOW;");
        }

    }

    @Deprecated
    private void resize(){
        double hw = mainHBox.getWidth()/2;
        double hh = mainHBox.getHeight()/2;
        final double CORR = 20;
        RVBox.setPrefWidth(hw/2);
        LVBox.setPrefWidth(hw/2);
        UpFieldHBox.setPrefHeight(hh/2);
        DownFieldHBox.setPrefHeight(hh/2);
        GameField.setPrefWidth(hw-CORR);
        GameField.setPrefHeight(hh - CORR);
        // calculate the number of columns and rows based on the number of colors and a golden ratio for layout.
    }

}