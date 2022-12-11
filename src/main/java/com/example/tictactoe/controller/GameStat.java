package com.example.tictactoe.controller;

import com.example.tictactoe.some.newGameStaff.GameController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import static com.example.tictactoe.Settings.TEST_MOD;

public class GameStat {

    @FXML
    public VBox TLVBox;
    @FXML
    public Pane LPane;
    @FXML
    public TextFlow LText;

    @FXML
    public VBox TRVBox;
    @FXML
    public TextFlow MLTText;
    @FXML
    public TextFlow MLBText;

    @FXML
    public VBox MRVBox;
    @FXML
    public TextFlow MRTText;
    @FXML
    public TextFlow MRBText;

    @FXML
    public VBox MLVBox;
    @FXML
    public Pane RPane;
    @FXML
    public TextFlow RText;



    @FXML
    public void initialize() {
        /* DEBUG */
        //if (TEST_MOD) {
            TLVBox.setStyle("-fx-background-color: GREEN;");
            MLVBox.getParent().setStyle("-fx-background-color: RED;");
            MRVBox.setStyle("-fx-background-color: BLUE;");
            TRVBox.setStyle("-fx-background-color: YELLOW;");
        //}
//        Text text1 = new Text("Big italic red text");
//        text1.setFill(Color.RED);
//        text1.setFont(Font.font("Helvetica", FontPosture.ITALIC, 40));
//        Text text2 = new Text(" little bold blue text");
//        text2.setFill(Color.BLUE);
//        text2.setFont(Font.font("Helvetica", FontWeight.BOLD, 10));
//        LText = new TextFlow(text1, text2);

    }

}
