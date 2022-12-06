package com.example.tictactoe.some.newGameStaff;

import com.example.tictactoe.some.figurs.Figura;
import com.example.tictactoe.some.figurs.Krestik;
import com.example.tictactoe.some.figurs.Nolik;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class GameController {

    private GridPane flore;
    private final Pane pane;
    private final int columCount, rowCount;
    private double floreHeight, floreWeight, segmentHeight,segmentWeight;
    private double lineStoke;
    private ArrayList<Segment> segments;
    private ArrayList<MyPlayerRecord_just_for_fName> players;
    private final int winLine = 3;
    private int playersMove = 0;
    public GameController(Pane pane,int columCount,int rowCount){
        this.pane = pane;
        this.columCount = columCount;
        this.rowCount = rowCount;

        players = new ArrayList<>();
        players.add(new MyPlayerRecord_just_for_fName(Figura.Name.krestik));
        players.add(new MyPlayerRecord_just_for_fName(Figura.Name.nolik));

        setNewSize();
    }
    private void createSegments(){
        segments = new ArrayList<>(columCount*rowCount);
        for (int i = 0;i < columCount*rowCount; i++)
            segments.add(new Segment(segmentHeight,segmentWeight));
        for (var i : segments)
            i.getGroup().setOnMouseClicked((event) -> {
                 newMove(i);
            });
    }
    private void drawSegments(){
        for(int i = 0; i < columCount; i++)
            for(int j = 0; j < rowCount; j++)
                flore.add(segments.get((columCount*i)+j).getGroup(),j,i);

    //        ///  DEBUG // нумерує сегменти
//        for (var i : segments) {
//            var v = new Label();
//            v.setText(String.valueOf(segments.indexOf(i)));
//            v.setFont(Font.font(30));
//            i.getGroup().getChildren().add(v);
//        }
    }
    public void setNewSize(){
        double h =pane.getPrefHeight(), w =pane.getPrefWidth();
        floreHeight = h;
        floreWeight = w;
        segmentHeight = (floreHeight - lineStoke*(columCount-1))  /columCount;
        segmentWeight = (floreWeight - lineStoke*(rowCount-1))    /rowCount;
        lineStoke = (Math.min(h,w)/100) * 2;
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
        double h = (floreHeight - lineStoke*(columCount-1))  /columCount;
        double w = (floreWeight - lineStoke*(rowCount-1))    /rowCount;
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
    public void newGame(){
        //clear();
        drawFlore();
        createSegments();
        drawSegments();
    }
    private void drawFlore(){
        flore = new GridPane();
        pane.getChildren().add(flore);
        flore.setVgap(lineStoke);
        flore.setHgap(lineStoke);
        /* DEBUG // TODO: remove */ flore.setStyle("-fx-background-color: RED;");
    }
    public void resize(){
        //clear();
        setNewSize();
        for(var i : segments) i.resize(segmentHeight,segmentWeight);
        drawFlore();
        drawSegments();
    }
    @Deprecated // недороблена  (чи потрібна?)
    private void clear(){
        flore.getChildren().clear();
        //pane.getChildren().clear();
    }

    // FIXME: 06.12.2022 баг, при ресайзі не вся площина панелі займається гадаю ( не завжди )
// resize is working
}


class Segment {
    private final Rectangle flor;
    private Figura figura = null;
    private final Group group;
    private static final double fSizePer = 90;

    Segment(double h, double w){
        flor = new Rectangle(w,h);
        group = new Group();
        flor.setFill(Color.WHITE);
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
    public void resize(double h, double w){
        flor.setWidth(w);
        flor.setHeight(h);
        if(!isEmpty()) {
            group.getChildren().remove(figura.get());
            setFigura(figura.getName());
        }
    }

}

record MyPlayerRecord_just_for_fName(Figura.Name fig){
}