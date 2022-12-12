package com.example.tictactoe;

import com.example.tictactoe.some.LogSinInOut;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GameStat {
    public enum Status{ not_working, work_but_no_module, working}
    private static Status status = Status.not_working;
    private static Pane pane = new Pane();

    public static void setNewStatus(Status status1){
        switch (status1){
            case not_working -> stop();
            case work_but_no_module -> clear();
            case working -> start();
            default -> throw new RuntimeException();
        }
    }

    public static void setPane(Pane pane){
        GameStat.pane = pane;
    }
    public static void start(){
        try {
            create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void clear(){
        pane.getChildren().clear();// not safe
        status = Status.work_but_no_module;
    }
    private static void stop(){
        //pane.getParent().getChildrenUnmodifiable().remove(pane);
        clear(); pane = new Pane();// not safe
        status = Status.not_working;
    }

    private static void create() throws IOException {
        clear();
        pane.getChildren().add(new FXMLLoader(HelloApplication.class.getResource("profile-view.fxml")).load());
        status = Status.working;
    }
}

