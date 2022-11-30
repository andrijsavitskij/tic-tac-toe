package com.example.tictactoe.some.figurs;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Nolik implements Figura {
    private Circle big;
    private Circle smol;
    private Group group;
    private Color mainColor = Color.BLACK;
    private Color secondColor = Color.WHITE;

    public Nolik(double X, double Y, double R){
        big = new Circle(X,Y,R, mainColor);
        smol = new Circle(X,Y,R-((R/100)*10),secondColor);
        group = new Group(big,smol);
    }
    @Override
    public Group getNew(double X, double Y, double R){
        return new Nolik(X,Y,R).getGroup();
    }
    @Override
    public Group getGroup(){
        return group;
    }

}
