package com.example.tictactoe.some.figurs;

import javafx.scene.Group;

public interface Figura {


    Group getNew(double X, double Y, double L);

    Group getGroup();

//    default String getName(){
//        if (this.getClass().equals(Nolik.class))    return FigName.Nolik.toString();
//        if (this.getClass().equals(Krestik.class))  return FigName.Krestik.toString();
//        return null;
//    }


}
