package com.example.tictactoe.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import org.kordamp.bootstrapfx.scene.layout.Panel;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController  {

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
    private Button bbb;

    @FXML
    public void initialize() {
        for(int i = 0; i < 10;i++) // ?????????????!!?!?!?!?!??!?? d(-_-)b <--- you  // line more black =)
            Platform.runLater(this::drawGF);

        GameField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GameField.getChildren().add(new Circle(event.getX(),event.getY(),5,Color.RED));
            }
        });

    }
    @FXML
    public void onclk(ActionEvent actionEvent) {
        drawGF();
    }

    private void drawGF(){
        var width = GameField.getWidth();
        var height = GameField.getHeight();
        Line hl1 = new Line(width / 3, 0, width / 3, height);
        Line hl2 = new Line((width / 3) * 2, 0, (width / 3) * 2, height);
        Line vl1 = new Line(0, height / 3, width, height / 3);
        Line vl2 = new Line(0, (height / 3) * 2, width, (height / 3) * 2);
        GameField.getChildren().add(new Group(hl1, hl2, vl1, vl2));
    }
}