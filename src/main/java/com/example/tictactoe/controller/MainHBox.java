package com.example.tictactoe.controller;

import com.example.tictactoe.Settings;
import com.example.tictactoe.some.LogSinInOut;
import com.example.tictactoe.some.newGameStaff.GameController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import static com.example.tictactoe.Settings.TEST_MOD;

public class MainHBox {

    @FXML
    private Pane GameField;
    @FXML
    private HBox DownFieldHBox;
    @FXML
    private VBox RVBox;
    private static GameController game;


    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            game = new GameController(GameField, Settings.gameColumn, Settings.gameRows, 3); // FIXME: 02.12.2022 только квадратные матрицы((
            game.newGame();
        });

        Platform.runLater(()->{
            Panel p = new Panel();
            RVBox.getChildren().add(p);
            LogSinInOut.setPane(p);
            LogSinInOut.start();
        });




        Platform.runLater(() -> {
            Button b = new Button();
            b.setText("new game");
            RVBox.getChildren().add(b);
            b.setOnAction(event -> {
                game.newGame();
            });
        });

        GameField.setStyle(Settings.Color.backGroundMain);
        GameField.getParent().setStyle(Settings.Color.backGroundSecond);
        RVBox.setStyle(Settings.Color.backGroundMain);
        DownFieldHBox.setStyle(Settings.Color.backGroundMain);


        /* DEBUG */
        if (TEST_MOD) {
            GameField.setStyle("-fx-background-color: GREEN;");
            GameField.getParent().setStyle("-fx-background-color: RED;");
            RVBox.setStyle("-fx-background-color: GREY;");
            DownFieldHBox.setStyle("-fx-background-color: YELLOW;");
        }

    }

    // FIXME: 25.12.2022 при розтягуванні відчуття що щось десь не видаляється і залишається у фоні(його видно при розтягуванні)

}