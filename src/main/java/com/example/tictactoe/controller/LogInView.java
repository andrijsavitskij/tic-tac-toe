package com.example.tictactoe.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class LogInView {
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
        TError.setText("go to singIn");
    }
    @FXML
    private void OnBLogin(){
        TError.setText("");
    }
}
