package com.example.tictactoe;

import com.example.tictactoe.some.Player;

public class Settings {
    static public int lineToWin = 1;
    static public int gameColumn = 3;
    static public int gameRows = 3;
    static public Player player = new Player("Player",0,0);

    static final public boolean TEST_MOD = true;

    static public void playerPlusWin(){
        player = new Player(player.name(), player.winCount() +1, player.id());
    }
    @Deprecated
    static public String Password = "1111";
    @Deprecated
    static public String Login = "admin";

    public static class Color{
        static final public String backGroundMain = "-fx-background-color: #DADADA;";
        static final public String backGroundSecond = "-fx-background-color: #D0D0D0;";
        static final public String backGroundRED = "-fx-background-color: RED;";
        static final public String game_fieldSepLine_BG = "-fx-background-color: #505050;";
        static final public String game_winLine_C = "#000000";
    }
}


