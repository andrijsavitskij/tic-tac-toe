package com.example.tictactoe.some;

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
    public void clear(){

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

    public boolean isEmpty(){
        return figura == null;
    }
}
