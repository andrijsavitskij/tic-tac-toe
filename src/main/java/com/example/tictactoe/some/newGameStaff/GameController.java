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
    public GameController(Pane pane,int columCount,int rowCount){
        flore = new GridPane();
        pane.getChildren().add(flore);
        flore.setVgap(lineStoke);
        flore.setHgap(lineStoke);
        this.columCount = columCount;
        this.rowCount = rowCount;
        setNewSize(pane.getPrefHeight(),pane.getPrefWidth());
        flore.setStyle("-fx-background-color: RED;");
        createSegments();
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
    }

    public void setNewSize(double h, double w){
        height = h;
        weight = w;
    }

}

class Segment {
    private final Rectangle flor;
    private Figura figura;
    private final Group group;
    private final double fSizePer =70;

    Segment(double h, double w){
        flor = new Rectangle(w,h);
        group = new Group();
                                            flor.setFill(Color.WHEAT);
        group.getChildren().add(flor);
    }

    public Figura.Name getFiguraName() {
        return figura.getName();
    }
    public void setFigura(@NotNull Figura.Name fName) {
        switch (fName){
            case krestik -> figura = new Krestik(flor.getX(),flor.getY(), (flor.getX()/100)*fSizePer);
            case nolik -> figura = new Nolik(flor.getX(),flor.getY(), (flor.getX()/100)*fSizePer);
        }
        group.getChildren().add(figura.get());
    }
    public Group getGroup(){
        return group;
    }
}