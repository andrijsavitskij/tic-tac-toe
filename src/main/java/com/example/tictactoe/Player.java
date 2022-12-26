package com.example.tictactoe;

import com.example.tictactoe.some.figurs.Figura;

public class Player {
    {
        name = "NULL";
        winCount = 0;
        id = -1;
        figura = null;
    }
    public Player(String name, int win, int id){
        this.name = name;
        this.winCount = win;
        this.id = id;
    }
    public Player(String name, int win, int id, Figura.Name figura){
        this.name = name;
        this.winCount = win;
        this.id = id;
        this.figura = figura;
    }
    public Player(){
    }
    public void setFigura(Figura.Name figura){
        this.figura = figura;
    }
    public String name;
    public int winCount;
    public long id;
    public Figura.Name figura = null;
}
