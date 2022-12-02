package com.example.tictactoe.controller;

import com.example.tictactoe.HelloApplication;
import com.example.tictactoe.some.LogSinInOut;
import com.example.tictactoe.some.gameStaff.Game;
import com.example.tictactoe.some.newGameStaff.GameController;
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
    private HBox mainHBox;
    private static Game game;


    @FXML
    public void initialize() {
        Platform.runLater(this::resize);
        Platform.runLater(()-> {
            new GameController(GameField,3,3);
            //game = new Game(GameField);
            //game.newGame();
        });


        //LogSinInOut.setPane(RVBox);
        //LogSinInOut.start();

        Platform.runLater(()-> {
            mainHBox.widthProperty().addListener((obs, oldVal, newVal) -> {
                resize();
            });

            mainHBox.heightProperty().addListener((obs, oldVal, newVal) -> {
                resize();
            });
        });


        ///* //---------DEBUG
        GameField.setStyle("-fx-background-color: GREEN;");
        GameField.getParent().setStyle("-fx-background-color: RED;");
        RVBox.setStyle("-fx-background-color: GREY;");
        LVBox.setStyle("-fx-background-color: GREY;");
        UpFieldHBox.setStyle("-fx-background-color: YELLOW;");
        DownFieldHBox.setStyle("-fx-background-color: YELLOW;");
        //*/

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
    }

}