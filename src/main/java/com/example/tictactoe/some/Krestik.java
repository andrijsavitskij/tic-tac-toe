package com.example.tictactoe.some;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Krestik implements Figura {
    private Line right;
    private Line left;
    private Group group;
    private Color mainColor = Color.BLACK;
    private Color secondColor = Color.WHITE;

    public Krestik(double X, double Y, double L) {
        right = new Line(X-(L/2), Y+(L/2), X+(L/2),Y-(L/2));
        left = new Line(X+(L/2), Y+(L/2), X-(L/2),Y-(L/2));
        group = new Group(right, left);
    }

    @Override
    public Group getNew(double X, double Y, double L) {
        return new Group(
                new Line(X-(L/2), Y+(L/2), X+(L/2),Y-(L/2)),
                new Line(X+(L/2), Y+(L/2), X-(L/2),Y-(L/2))
        );
    }

    @Override
    public Group getGroup() {
        return group;
    }

}
