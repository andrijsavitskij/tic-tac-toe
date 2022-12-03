package com.example.tictactoe.some.newGameStaff;

import com.example.tictactoe.some.figurs.Figura;
import com.example.tictactoe.some.figurs.Krestik;
import com.example.tictactoe.some.figurs.Nolik;
import com.example.tictactoe.some.gameStaff.Title;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;

public class GameController {

    private final GridPane flore;

    private final Pane pane;
    private final int columCount, rowCount;
    private double height, weight;
    private double lineStoke = 5;
    private ArrayList<Segment> segments;
    private ArrayList<MyPlayerRecord_just_for_fName> players;
    private final int winLine = 3;
    private int playersMove = 0;
    public GameController(Pane pane,int columCount,int rowCount){
        this.pane = pane;
        flore = new GridPane();
        pane.getChildren().add(flore);
        flore.setVgap(lineStoke);
        flore.setHgap(lineStoke);
        this.columCount = columCount;
        this.rowCount = rowCount;
        setNewSize(pane.getPrefHeight(),pane.getPrefWidth());
                                        /* TEST */                            flore.setStyle("-fx-background-color: RED;"); // TODO: remove
        createSegments();

        players = new ArrayList<>();
        players.add(new MyPlayerRecord_just_for_fName(Figura.Name.krestik));
        players.add(new MyPlayerRecord_just_for_fName(Figura.Name.nolik));
    }
    private void createSegments(){
        segments = new ArrayList<>(columCount*rowCount);
        double h = (height - lineStoke*(columCount-1))  /columCount;
        double w = (weight - lineStoke*(rowCount-1))    /rowCount;
        for (int i = 0;i < columCount*rowCount; i++)
            segments.add(new Segment(h,w));

        for(int i = 0; i < columCount; i++)
            for(int j = 0; j < rowCount; j++)
                flore.add(segments.get((columCount*i)+j).getGroup(),i,j);

        for (var i : segments)
            i.getGroup().setOnMouseClicked((event) -> {
                 newMove(i);
            });
        for (var i : segments) {
            var v = new Label();
            v.setText(String.valueOf(segments.indexOf(i)));
            v.setFont(Font.font(30));
            i.getGroup().getChildren().add(v);
        }
    }
    public void setNewSize(double h, double w){
        height = h;
        weight = w;
    }
    private void newMove(Segment segment){
        if(segment.isEmpty()){
            segment.setFigura(players.get(playersMove++).fig());
            if(playersMove >= players.size()) playersMove = 0;
            win(segment);
        }
    }

    private void win(Segment segment){
        // 1) собрать все (x)+-(n-1) елементи в масив
        // 2) найти n елемента с одинаковой
        // 3) нарисовать линию через элементы

        ArrayList<Segment> list = new ArrayList<>();
        final int ID = segments.indexOf(segment);
        // ----------------------------------------------  -
        {
            for (int i = ID - (winLine - 1); i < ID + winLine - 1; i++)
                if (i >= 0 && i < segments.size())
                    list.add(segments.get(i));
            var v = myBestFun(list, segment);
            if (null != v) {
                myBestestFum(v[0], v[1]);
                return;
            }
        }
        // ----------------------------------------------  |
        {
            for (int i = ID - ((winLine - 1) * columCount); i < ID + ((winLine - 1) * columCount); i += columCount) {
                if (i >= 0 && i < segments.size())
                    list.add(segments.get(i));
            }
            var v = myBestFun(list, segment);
            if (null != v) {
                myBestestFum(v[0], v[1]);
                return;
            }
        }
        // ----------------------------------------------  \
        {
            for (int i = ID - ((winLine - 1) * columCount) - 1; i < ID + ((winLine - 1) * columCount) + 1; i += columCount + 1)
                if (i >= 0 && i < segments.size())
                    list.add(segments.get(i));
            var v = myBestFun(list, segment);
            if (null != v) {
                myBestestFum(v[0], v[1]);
                return;
            }
        }
        // ----------------------------------------------  /
        {
            for (int i = ID - ((winLine - 1) * columCount) + 1; i < ID + ((winLine - 1) * columCount) - 1; i += columCount - 1)
                if (i >= 0 && i < segments.size())
                    list.add(segments.get(i));
            var v = myBestFun(list, segment);
            if (null != v) {
                myBestestFum(v[0], v[1]);
                return;
            }
        }
    }

    /**
     * @return Segment if WIN or null if not WIN;
     */
    private @Nullable Segment[] myBestFun(ArrayList<Segment> list, Segment segment){
        int cc = 0;
        Segment ss = null;
        for (var i : list) {
            if ( !i.isEmpty() && i.getFiguraName().equals(segment.getFiguraName())) {
                if (++cc == 1) {
                    ss = i;
                }
            }
            else{
                cc = 0;
            }
            if (cc == winLine) {
                return new Segment[]{ss, i};
            }
        }
        return null;
    }
    private void myBestestFum(Segment start, Segment end){
//        double sx = start.getGroup().getChildren().get(0).getScaleX();
//        double sy = start.getGroup().getScaleY();
//        double ex = end.getGroup().getScaleX();
//        double ey = end.getGroup().getScaleY();
        double h = (height - lineStoke*(columCount-1))  /columCount;
        double w = (weight - lineStoke*(rowCount-1))    /rowCount;
        double sx = start.getGroup().getLayoutX()+(w/2);
        double sy = start.getGroup().getLayoutY()+(h/2);
        double ex = end.getGroup().getLayoutX()+(w/2);
        double ey = end.getGroup().getLayoutY()+(h/2);
        //Line line = new Line(start.getGroup().getLayoutX(),start.getGroup().getLayoutY(),start.getGroup().getLayoutX(),start.getGroup().getLayoutY());
        Line line = new Line(sx,sy,ex,ey);
        line.setStrokeWidth(lineStoke*2);
        line.setStroke(Color.DARKBLUE);
        line.toFront();
        pane.getChildren().add(line);

        //// FIXME: 03.12.2022 АААААААа диаганали не работают, линия рисуются ровно от центра (не оч че-то)
    }
}



class Segment {
    private final Rectangle flor;
    private Figura figura = null;
    private final Group group;
    private final double fSizePer =90;

    Segment(double h, double w){
        flor = new Rectangle(w,h);
        group = new Group();
                            /* TEST */                 flor.setFill(Color.WHEAT);// TODO: remove
        group.getChildren().add(flor);
    }

    public Figura.Name getFiguraName() {
        return figura.getName();
    }
    public boolean isEmpty(){
        return figura == null;
    }
    public void setFigura(@NotNull Figura.Name fName) {
        double cx = (flor.getWidth() - flor.getX()) /2;
        double cy = (flor.getHeight() - flor.getY()) /2;
        double size = (Math.min(flor.getHeight(),flor.getWidth())/2/100) * fSizePer;
        switch (fName){
            case krestik -> figura = new Krestik(cx, cy, size);
            case nolik -> figura = new Nolik(cx, cy, size);
        }
        group.getChildren().add(figura.get());
    }
    public Group getGroup(){
        return group;
    }


}

record MyPlayerRecord_just_for_fName(Figura.Name fig){
}