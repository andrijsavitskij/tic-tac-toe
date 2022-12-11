package com.example.tictactoe;

import com.example.tictactoe.some.newGameStaff.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainHBox-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),800,500);//1280,720);
        //stage.setResizable(false); // no resize
        stage.setTitle("Tic-Tac-Toe");
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}