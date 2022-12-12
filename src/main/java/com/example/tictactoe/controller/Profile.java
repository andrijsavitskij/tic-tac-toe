package com.example.tictactoe.controller;

import com.example.tictactoe.some.LogSinInOut;
import com.example.tictactoe.Settings;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Timer;
import java.util.TimerTask;

public class Profile {
    @FXML
    private AnchorPane APMain;
    @FXML
    private Button BSLogout;
    @FXML
    private Label TWin;
    @FXML
    private Label TLogin;

    private Timer upWinCount;

    @FXML
    public void initialize(){
        TLogin.setText(Settings.player.name());
        updateWinCount();
        upWinCount = new Timer();
        upWinCount.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(()->updateWinCount());
            }
        }, 0, 300);
    }

    public void updateWinCount(){
        TWin.setText(String.valueOf(Settings.player.winCount()));
    }

    @FXML
    private void OnBLogout(){
        Settings.player = null;
        upWinCount.cancel();
        LogSinInOut.setNewStatus(LogSinInOut.Status.logIn);
    }

}
