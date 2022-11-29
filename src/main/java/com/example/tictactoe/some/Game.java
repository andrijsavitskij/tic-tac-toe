package com.example.tictactoe.some;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private final Pane field;
    private ArrayList<Title> titles = null;
    private static boolean move = false;
    public Game(Pane field){
        this.field = field;
    }
    public void newGame(){
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

        field.setOnMouseClicked(event -> onClick(event.getX(), event.getY()));
    }


    private void newMove(Title title){
        if(move) title.setFigura(Krestik.class);
        else title.setFigura(Nolik.class);
        field.getChildren().add(title.getFigura().getGroup());
        move = !move;
    }

    public void onClick(Double X, Double Y) {
        for (var i : titles) {
            if (i.isEmpty() && i.isIn(X, Y)) {
                newMove(i);
                whoWin(titles.indexOf(i));
                break;
            }
        }
    }

    private void whoWin(int id){
        if(!titles.get(0).isEmpty() || !titles.get(4).isEmpty() || !titles.get(4).isEmpty()) {
            var v = titles.get(id);
            final int MX = 3, MY = 3, LINE = 3;

            if (v.equals(titles.get(hor_ver(id,1,0))) && v.equals(titles.get(hor_ver(id,2,0)))) {
                winLine(id,0);// --
            }
            else if (v.equals(titles.get(hor_ver(id,0,1))) && v.equals(titles.get(hor_ver(id,0,2)))) {
                winLine(id,1);// |
            }
            else if ((id==0 || id == 4 || id== 8) && (titles.get(0).equals(titles.get(4)) && titles.get(0).equals(titles.get(8)))) {
                winLine(id,2);// \
            }
            else if ((id==2 || id == 4 || id== 6) && (titles.get(2).equals(titles.get(4)) && titles.get(2).equals(titles.get(6)))) {
                winLine(id,3);// /
                // FIX 29.11.2022
            }
        }
    }
    private int hor_ver(int id, int X, int Y){
        int xmax = 3, ymax = 3;
        int x = id % 3, y = id / 3;
        x += x + X >= xmax ? (X - xmax) : X;
        y += y + Y >= ymax ? (Y - ymax) : Y;
        return x + y * 3;
    }

    private int rdig_ldig(int id, int X, int Y){
        int xmax = 3, ymax = 3;
        int x = id % 3, y = id / 3;
        x += x + X >= xmax ? (X - xmax) : X;
        y += y + Y >= ymax ? (Y - ymax) : Y;
        return x + y * 3;
    }

    private void winLine(int id, int ll){
        var v = titles.get(id);
        Line line;
        int zero = 10;
        switch (ll){
            // --
            case 0-> line = new Line(zero,v.getCenterY(),field.getWidth(),v.getCenterY());
            // |
            case 1-> line = new Line(v.getCenterX(),zero,v.getCenterX(),field.getHeight());
            // \
            case 2-> line = new Line(zero,zero,field.getWidth(),field.getHeight());
            // /
            case 3-> line = new Line(field.getWidth(),zero,zero,field.getHeight());
            default -> line = new Line(100,100,200,200);
        }
        line.setStrokeWidth(6);
        line.setStroke(Color.DARKBLUE);
        field.getChildren().add(line);
        Platform.runLater(()-> field.setOnMouseClicked(event -> newGame()));

    }



    private void drawGF(){
        var width = field.getWidth();
        var height = field.getHeight();
        Line hl1 = new Line(width / 3, 0, width / 3, height);
        Line hl2 = new Line((width / 3) * 2, 0, (width / 3) * 2, height);
        Line vl1 = new Line(0, height / 3, width, height / 3);
        Line vl2 = new Line(0, (height / 3) * 2, width, (height / 3) * 2);
        hl1.setStrokeWidth(5);
        hl2.setStrokeWidth(5);
        vl1.setStrokeWidth(5);
        vl2.setStrokeWidth(5);
        field.getChildren().add(new Group(hl1, hl2, vl1, vl2));
    }

}
