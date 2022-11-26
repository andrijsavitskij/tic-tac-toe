package com.example.tictactoe.some;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Nolik{
    private Circle big;
    private Circle smol;
    private Group group;
    private Color mainColor = Color.BLACK;
    private Color secondColor = Color.WHITE;

    public Nolik(double X, double Y, double R){
        big = new Circle(X,Y,R, mainColor);
        smol = new Circle(X,Y,R-((R/100)*90),secondColor);
        group = new Group(big,smol);
    }
    public static Group getNew(double X, double Y, double R){
        return new Group(new Circle(X,Y,R,Color.BLACK),new Circle(X,Y,R-((R/100)*10),Color.WHITE));
    }
    public Group getGroup(){
        return group;
    }
}
