package com.example.tictactoe.some;

import javafx.fxml.FXML;

public class Settings {

    static public Player player = null;

    static public void playerPlusWin(){
        player = new Player(player.name(), player.winCount() +1, player.id());
    }
    @Deprecated
    static public String Password = "1111";
    @Deprecated
    static public String Login = "admin";


}
