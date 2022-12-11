package com.example.tictactoe.controller;

import com.example.tictactoe.some.LogSinInOut;
import com.example.tictactoe.some.Player;
import com.example.tictactoe.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SingIn {
    @FXML
    private AnchorPane APMain;
    @FXML
    private Text TError;
    @FXML
    private TextField TFLogin;
    @FXML
    private TextField TFPass;
    @FXML
    private TextField TFPassAgain;
    @FXML
    private Button BLogin;
    @FXML
    private Button BSingin;
    @FXML
    private void OnBSingin(){
        if(TFPass.getText().equals(TFPassAgain.getText())){
            Settings.Password = TFPass.getText(); Settings.Login = TFLogin.getText();
            Settings.player = new Player(TFLogin.getText(),0,1);
            LogSinInOut.setNewStatus(LogSinInOut.Status.profile);
        }else {
            TError.setText("Password not matching");
        }
    }
}
