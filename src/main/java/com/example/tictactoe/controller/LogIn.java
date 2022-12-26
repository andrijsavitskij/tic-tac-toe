package com.example.tictactoe.controller;

import com.example.tictactoe.some.LogSinInOut;
import com.example.tictactoe.Player;
import com.example.tictactoe.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class LogIn {
    @FXML
    private AnchorPane APMain;
    @FXML
    private Text TError;
    @FXML
    private TextField TFLogin;
    @FXML
    private TextField TFPass;
    @FXML
    private Button BLogin;
    @FXML
    private Button BSingin;

    @FXML
    private void OnBSingin(){
        LogSinInOut.setNewStatus(LogSinInOut.Status.singIn);
    }
    @FXML
    private void OnBLogin(){
        //        // must be login fun
        if(TFLogin.getText().equals(Settings.Login) && TFPass.getText().equals(Settings.Password) /* don't have DB with password(( && player.getPassword() == "1111"*/){
            Settings.player = new Player(TFLogin.getText(),0,1);
            LogSinInOut.setNewStatus(LogSinInOut.Status.profile);
        }else {
            TError.setText("Wrong login or password");
        }
    }
}
