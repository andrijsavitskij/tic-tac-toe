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
    private ArrayList<Title> titles = null;
    private static boolean move = false;
    public Game(){}
    public void newGame(Pane field){
        this.field = field;
        field.getChildren().clear();// bad
        drawGF();
        titles = new ArrayList<>(9);

        double width = field.getWidth()/3;
        double height = field.getHeight()/3;
        double x = 0, y = 0;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                titles.add(new Title(x,y,x+width,y+height));
                x+=width;
            }
            x = 0;
            y+=height;
        }

        move = false;
    }


    private void newMove(Title title){
        if(move) title.setFigura(Krestik.class);
        else title.setFigura(Nolik.class);
        field.getChildren().add(title.getFigura().getGroup());
        move = !move;
    }

    public void onClick(Double X, Double Y) {
        for (var i : titles) {
            if (i.isEmpty() & i.isIn(X, Y)) {
                newMove(i);
                whoWin(titles.indexOf(i));
                break;
            }
        }
    }

    private void whoWin(int id){
        if(!titles.get(0).isEmpty() || !titles.get(4).isEmpty() || !titles.get(4).isEmpty()) {
            var v = titles.get(id);

            int x = id % 3;
            if (v.equals(titles.get(matrix(id,1,0))) && v.equals(titles.get(matrix(id,2,0)))) {
                winLine(id,0);//horizontal
            }
            //else if (v.equals(titles.get() && titles.get())) {

            //}
        }
    }

    private void winLine(int id, int ll){
        switch (ll){
            case 0->{
                newGame(field);
            }
            case 1->{
            }
            case 2->{
            }
            case 3->{
            }
        }
    }

    private int matrix(int id, int X, int Y){
        int xmax = 3, ymax = 3;
        int x = id%3, y = id/3;
        x+= x+X >= xmax? +(X-xmax) : X;
        y+= y+Y >= ymax? +(Y-ymax) : Y;
        return x+y*3;
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
