package com.example.tictactoe.some.figurs;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public interface Figura {
    Group getNew(double X, double Y, double L);

    public enum Name {
        krestik,nolik
    }
    Group get();

    Name getName();

}
