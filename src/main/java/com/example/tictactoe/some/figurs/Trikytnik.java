package com.example.tictactoe.some.figurs;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Trikytnik implements Figura{
    private final Group group;

    public Trikytnik(double X, double Y, double L) {
        Line right = new Line(X + (L / 2), Y - (L / 2), X, Y + (L / 2));
        Line left = new Line(X - (L / 2), Y - (L / 2), X , Y + (L / 2));
        Line down = new Line(X - (L / 2), Y - (L / 2), X + (L / 2), Y - (L / 2));
        left.setStrokeWidth(5);
        right.setStrokeWidth(5);
        down.setStrokeWidth(5);
        group = new Group(right, left, down);
    }

    @Override
    public Group getNew(double X, double Y, double L) {
        return new Trikytnik(X,Y,L).get();
    }

    @Override
    public Group get() {
        return group;
    }

    @Override
    public Name getName(){
        return Name.trikytnik;
    }

}
