package com.example.tictactoe.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

import static com.example.tictactoe.Settings.TEST_MOD;

public class GameStat {

    @FXML
    public HBox mainHBox;
    @FXML
    public VBox LVBox;
    @FXML
    public Pane LPane;
    @FXML
    public Label LLabel;
    @FXML
    public VBox RVBox;
    @FXML
    public Pane RPane;
    @FXML
    public Label RLabel;


    @FXML
    public void initialize() {
        /* DEBUG */
        if (TEST_MOD) {
            LVBox.setStyle("-fx-background-color: GREEN;");
            RVBox.getParent().setStyle("-fx-background-color: RED;");
            LPane.setStyle("-fx-background-color: BLUE;");
            RPane.setStyle("-fx-background-color: LIGHTBLUE;");
        }
        Platform.runLater(()->{
            LLabel.setText("Big italic red text");
            LLabel.setFont(Font.font("Helvetica", FontPosture.ITALIC, 10));
            RLabel.setText("little bold blue text");
            RLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
        });
        Platform.runLater(() -> {
            mainHBox.widthProperty() .addListener((obs, oldVal, newVal) -> resize());
            mainHBox.heightProperty().addListener((obs, oldVal, newVal) -> resize());
        });

    }

    @Deprecated
    private void resize(){
        double hw = mainHBox.getWidth()/2;
        double hh = mainHBox.getHeight()/2;
        final double CORR = 20;
        RVBox.setPrefWidth(hw/2);
        LVBox.setPrefWidth(hw/2);

        // calculate the number of columns and rows based on the number of colors and a golden ratio for layout.
    }

}
