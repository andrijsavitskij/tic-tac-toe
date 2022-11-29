package com.example.tictactoe.some;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Krestik implements Figura {
    private final Group group;

    public Krestik(double X, double Y, double L) {
        Line right = new Line(X - (L / 2), Y + (L / 2), X + (L / 2), Y - (L / 2));
        Line left = new Line(X + (L / 2), Y + (L / 2), X - (L / 2), Y - (L / 2));
        left.setStrokeWidth(5);
        right.setStrokeWidth(5);
        group = new Group(right, left);
    }

    @Override
    public Group getNew(double X, double Y, double L) {
        return new Krestik(X,Y,L).getGroup();
    }

    @Override
    public Group getGroup() {
        return group;
    }


}
