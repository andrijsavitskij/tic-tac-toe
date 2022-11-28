package com.example.tictactoe.controller;

import com.example.tictactoe.some.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


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
    private Text t1;
    @FXML
    private Text t2;
    @FXML
    private Text t3;
    @FXML
    private AnchorPane ancor;

    private static Game game;

    @FXML
    public void initialize() {
        game = new Game();
        game.newGame(GameField);

        ancor.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                t1.setText("X = "+ String.valueOf(event.getX()) +"; Y =  " + event.getY() );
                t2.setText("X = "+ event.getSceneX() +"; Y =  " + event.getSceneY() );
                t3.setText("X = "+ event.getScreenX() +"; Y =  " + event.getScreenY() );
            }
        });
        GameField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                t1.setText("X = "+ String.valueOf(event.getX()) +"; Y =  " + event.getY() );
                t2.setText("X = "+ event.getSceneX() +"; Y =  " + event.getSceneY() );
                t3.setText("X = "+ event.getScreenX() +"; Y =  " + event.getScreenY() );
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        game.onClick(event.getX(), event.getY());
                    }
                });

                //GameField.getChildren().add(new Circle(event.getX(),event.getY(),5,Color.RED));
            }
        });

        //        GameField.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if(event.getButton().equals(MouseButton.PRIMARY)) {
//                    GameField.getChildren().add(Nolik.getNew(event.getX(), event.getY(), 60
//                    ));
//                }
//                if(event.getButton().equals(MouseButton.SECONDARY))  {
//                    GameField.getChildren().add(Krestik.getNew(event.getX(), event.getY(), 60));
//                }
//                //GameField.getChildren().add(new Circle(event.getX(),event.getY(),5,Color.RED));
//            }
//        });

    }
    @FXML
    public void onClk(ActionEvent actionEvent) {
        GameField.getChildren().clear();
        game.newGame(GameField);
    }





}