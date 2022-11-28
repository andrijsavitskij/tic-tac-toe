package com.example.tictactoe.some;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private Pane field;
    private Title[][] titles = new Title[3][3];
    private static boolean move = false;
    public Game(){}
    public void newGame(Pane field){
        this.field = field;
        drawGF();
        double width = field.getWidth()/3;
        double height = field.getHeight()/3;
        double x = 0, y = 0;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                titles[i][j] = new Title(x,y,x+width,y+height);
                x+=width;
            }
            y+=height;
        }
        //flushTitles();

        move = false;
    }

    private void flushTitles(){
        for (var i : titles)
            for (var j : i)
                j.clear();
    }

    private void newMove(Title title){
        if(move) title.setFigura(new Krestik(field.getWidth()/3/2,field.getHeight()/3/2,80)); //todo redo
        else title.setFigura(new Nolik(field.getWidth()/3/2,field.getHeight()/3/2,60)); //todo redo
        field.getChildren().add(title.getFigura().getGroup());
        move = !move;
    }

    public void onClick(Double X, Double Y){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(titles[i][j].isEmpty() & titles[i][j].isIn(X,Y)) newMove(titles[i][j]);
            }
        }
    }

    private void whoWin(){

    }

    private void drawGF(){
        var width = field.getWidth();
        var height = field.getHeight();
        Line hl1 = new Line(width / 3, 0, width / 3, height);
        Line hl2 = new Line((width / 3) * 2, 0, (width / 3) * 2, height);
        Line vl1 = new Line(0, height / 3, width, height / 3);
        Line vl2 = new Line(0, (height / 3) * 2, width, (height / 3) * 2);
        field.getChildren().add(new Group(hl1, hl2, vl1, vl2));
    }

}
