package com.example.tictactoe.some.gameStaff;

import com.example.tictactoe.some.figurs.Figura;
import com.example.tictactoe.some.figurs.Krestik;
import com.example.tictactoe.some.figurs.Nolik;

public class Title {
    private Figura figura = null;
    private final double startX,startY;
    private final double endX,endY;

    Title(double sX,double sY, double eX, double eY){
        startX = sX;
        startY = sY;
        endY = eY;
        endX = eX;
    }

    public double getCenterX(){
        return endX -(endX-startX)/2;
    }

    public double getCenterY(){
        return endY -(endY-startY)/2;
    }

    public boolean isIn(double X, double Y){
        return X > startX && X < endX & Y > startY && Y < endY;
    }

    public Figura getFigura() {
        return figura;
    }

    public void setFigura(Figura figura){
        this.figura = figura;
    }

    public void setFigura(Class figuraClass){
        double L = Math.min(endX - startX, endY - startY)/2;
        if (Nolik.class.equals(figuraClass)){
            figura = new Nolik(getCenterX(),getCenterY(), (L/100)*70);
        }
        else if (Krestik.class.equals(figuraClass)){
            figura = new Krestik(getCenterX(),getCenterY(), L);
        }
    }

    public boolean isEmpty(){
        return figura == null;
    }

    public boolean equals(Title title){
        if(isEmpty() || title.isEmpty()) return false;
        return getFigura().getClass().equals(title.getFigura().getClass());
    }

    public void clear(){
        if(!isEmpty()) figura = null;
    }
}
