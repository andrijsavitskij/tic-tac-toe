package com.example.tictactoe.some.newGameStaff;

import com.example.tictactoe.some.figurs.Figura;
import com.example.tictactoe.some.figurs.Krestik;
import com.example.tictactoe.some.figurs.Nolik;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import java.util.ArrayList;

public class GameController {

    private final GridPane flore;
    private final int columCount, rowCount;
    private double height, weight;
    private double lineStoke = 1;
    private ArrayList<Segment> segments;
    private ArrayList<MyPlayerRecord_just_for_fName> players;
    private int playersMove = 0;
    public GameController(Pane pane,int columCount,int rowCount){
        flore = new GridPane();
        pane.getChildren().add(flore);
        flore.setVgap(lineStoke);
        flore.setHgap(lineStoke);
        this.columCount = columCount;
        this.rowCount = rowCount;
        setNewSize(pane.getPrefHeight(),pane.getPrefWidth());
                                        /* TEST */                            flore.setStyle("-fx-background-color: RED;");
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
    }
    public void setNewSize(double h, double w){
        height = h;
        weight = w;
    }
    private void newMove(Segment segment){
        if(segment.isEmpty()){
            segment.setFigura(players.get(playersMove++).fig());
            if(playersMove >= players.size()) playersMove = 0;
        }
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
                            /* TEST */                 flor.setFill(Color.WHEAT);
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