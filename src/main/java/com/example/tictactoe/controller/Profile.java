package com.example.tictactoe.controller;

import com.example.tictactoe.some.LogSinInOut;
import com.example.tictactoe.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Profile {
    @FXML
    private AnchorPane APMain;
    @FXML
    private Button BSLogout;
    @FXML
    private Label TWin;
    @FXML
    private Label TLogin;

    @FXML
    public void initialize(){
        TLogin.setText(Settings.player.name());
        updateWinCount();
        APMain.setOnMouseMoved(event -> updateWinCount());
    }

    public void updateWinCount(){
        TWin.setText(String.valueOf(Settings.player.winCount()));
    }

    @FXML
    private void OnBLogout(){
        Settings.player = null;
        LogSinInOut.setNewStatus(LogSinInOut.Status.logIn);
    }

}
