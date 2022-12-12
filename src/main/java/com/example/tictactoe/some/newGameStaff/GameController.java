package com.example.tictactoe.some.newGameStaff;

import com.example.tictactoe.Settings;
import com.example.tictactoe.some.figurs.Figura;
import com.example.tictactoe.some.figurs.Krestik;
import com.example.tictactoe.some.figurs.Nolik;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static com.example.tictactoe.Settings.TEST_MOD;
import static com.example.tictactoe.Settings.playerPlusWin;

public class GameController {

    private GridPane flore;
    private final Pane pane;
    private final int col, row;
    private double floreHeight, floreWeight, segmentHeight,segmentWeight;
    private double lineStoke;
    private ArrayList<Segment> segments;
    private ArrayList<MyPlayerRecord_just_for_fName> players;
    private final int winLine;
    private int playersMove = 0;
    private ArrayList<Segment[]> arrLineToDraw;

    /**
     * Конструктор
     */
    public GameController(Pane pane,int columns,int rows, int winLine){
        this.pane = pane;
        this.col = columns;
        this.row = rows;
        this.winLine = winLine;

        players = new ArrayList<>();
        players.add(new MyPlayerRecord_just_for_fName(Figura.Name.krestik));
        players.add(new MyPlayerRecord_just_for_fName(Figura.Name.nolik));

        newSize();
    }
    /**
     * Перемальовує все поле при зміні розмірів
     */
    public void resize(){
        //clear();
        newSize();
        for(var i : segments) i.resize(segmentHeight,segmentWeight);
        drawFlore();
        drawSegments();
        for(var i : arrLineToDraw) drawLine(i);
    }
    /**
     * Запускає нову гру
     */
    public void newGame(){
        //clear();
        drawFlore();
        createSegments();
        drawSegments();
        arrLineToDraw = new ArrayList<Segment[]>(); //todo to clear
    }

    private void drawSegments(){
        for(int i = 0; i < col; i++)
            for(int j = 0; j < row; j++)
                flore.add(segments.get((col *i)+j).getGroup(),j,i);

        //        ///  DEBUG // нумерує сегменти
//        for (var i : segments) {
//            var v = new Label();
//            v.setText(String.valueOf(segments.indexOf(i)));
//            v.setFont(Font.font(30));
//            i.getGroup().getChildren().add(v);
//        }
    }
    private void drawFlore(){
        flore = new GridPane();
        pane.getChildren().add(flore);
        flore.setVgap(lineStoke);
        flore.setHgap(lineStoke);
        flore.setStyle(Settings.Color.game_fieldSepLine_BG);
        if(TEST_MOD) flore.setStyle("-fx-background-color: RED;");
    }
    private void newMove(Segment segment){
        if(!segment.isNoEmpty()){
            segment.setFigura(players.get(playersMove++).fig());
            if(playersMove >= players.size()) playersMove = 0;
            win(segment);
            ifWin();
        }
    }
    private void newSize(){
        double h =pane.getPrefHeight(), w =pane.getPrefWidth();
        floreHeight = h;
        floreWeight = w;
        segmentHeight = (floreHeight - lineStoke*(col -1))  / col;
        segmentWeight = (floreWeight - lineStoke*(row -1))    / row;
        lineStoke = (Math.min(h,w)/100) * 2;
    }
    private void createSegments(){
        segments = new ArrayList<>(col * row);
        for (int i = 0; i < col * row; i++)
            segments.add(new Segment(segmentHeight,segmentWeight));
        for (var i : segments)
            i.getGroup().setOnMouseClicked((event) -> {
                 newMove(i);
            });

        /* DEBUG */ if(TEST_MOD) {
            for (var i : segments) {
                var v = new Label();
                v.setText(String.valueOf(segments.indexOf(i)));
                v.setFont(Font.font(10));
                i.getGroup().getChildren().add(v);
            }
        }
    }
    private void win(Segment segment) {
        final int[] ID = toMat(segments.indexOf(segment));
        final int WL = (winLine - 1);
        Lambda hor = () -> {
            ArrayList<Segment> arr = new ArrayList<>();
            for (int i = ID[0] - WL,j = ID[1]; i <= ID[0] + WL; i++)
                if(i>=0 && i < row && j >= 0 && j < col)
                    arr.add(segments.get(toMat(new int[]{i,j})));
            return arr;
        };//--  -
        Lambda ver = () -> {
            ArrayList<Segment> arr = new ArrayList<>();
            for (int i = ID[0],j = ID[1] - WL; j <= ID[1] + WL; j++)
                if(i>=0 && i < row && j >= 0 && j < col)
                    arr.add(segments.get(toMat(new int[]{i,j})));
            return arr;
        };//--  |
        Lambda rdi = () -> {
            ArrayList<Segment> arr = new ArrayList<>();
            for (int i = ID[0] - WL,j = ID[1] - WL; i <= ID[0] + WL || j <= ID[1] + WL; i++,j++)
                if(i>=0 && i < row && j >= 0 && j < col)
                    arr.add(segments.get(toMat(new int[]{i,j})));
            return arr;
        };//--  \
        Lambda ldi = () -> {
            ArrayList<Segment> arr = new ArrayList<>();
            for (int i = ID[0] + WL,j = ID[1] - WL; i >= ID[0] - WL && j <= ID[1] + WL; i--,j++)
                if(i>=0 && i < row && j >= 0 && j < col)
                    arr.add(segments.get(toMat(new int[]{i,j})));
            return arr;
        };//--  /

        drawLine(isWin(segment, hor));
        drawLine(isWin(segment, ver));
        drawLine(isWin(segment, rdi));
        drawLine(isWin(segment, ldi));

        //
    }
    // 0 - x, 1 - y;
    @Contract(value = "_ -> new", pure = true)
    private int @NotNull [] toMat(int id){
        return new int[]{id % row,id / col};
    }
    @Contract(value = "_ -> new",pure = true)
    private int toMat(int @NotNull [] mat){
        return mat[0]+(mat[1]* col);
    }
    /**
     * @return Segment if WIN or null if not WIN;
     */
    private @Nullable Segment[] isWin(Segment segment, Lambda l){
        ArrayList<Segment> list = l.run();
        int cc = 0;
        Segment ss = null;
        for (var i : list) {
            if ( i.isNoEmpty() && i.getFiguraName().equals(segment.getFiguraName())) {
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
    private void drawLine(Segment[] segs){
        if(segs == null) return;
        if(!arrLineToDraw.contains(segs)) arrLineToDraw.add(segs);
        Segment start = segs[0], end = segs[1];
        double h = (floreHeight - lineStoke*(col -1))  / col;
        double w = (floreWeight - lineStoke*(row -1))    / row;
        double sx = start.getGroup().getLayoutX()+(w/2);
        double sy = start.getGroup().getLayoutY()+(h/2);
        double ex = end.getGroup().getLayoutX()+(w/2);
        double ey = end.getGroup().getLayoutY()+(h/2);
        Line line = new Line(sx,sy,ex,ey);
        line.setStrokeWidth(lineStoke*2);
        line.setStroke(Paint.valueOf(Settings.Color.game_winLine_C));
        line.toFront();
        pane.getChildren().add(line);
    }
    private void ifWin() {
        //WIN
        if (arrLineToDraw.size() >= Settings.lineToWin) { // for future
            flore.setOnMouseClicked(event -> {
                flore.setOnMouseClicked(event1 -> {});
                newGame();
            });
            for (var i : segments)
                i.getGroup().setOnMouseClicked(event -> {
                    flore.setOnMouseClicked(event1 -> {});
                    newGame();
                });
            playerPlusWin();
        }
        //DRAW
        else if(segments.stream().allMatch(Segment::isNoEmpty)){
            flore.setOnMouseClicked(event -> {
                flore.setOnMouseClicked(event1 -> {});
                newGame();
            });
            for (var i : segments)
                i.getGroup().setOnMouseClicked(event -> {
                    flore.setOnMouseClicked(event1 -> {});
                    newGame();
                });
        }
    }
    @Deprecated // недороблена (чи потрібна?)
    private void clear(){
        flore.getChildren().clear();
        //pane.getChildren().clear();
    }

    // FIXME--low--: 06.12.2022 баг, при ресайзі не вся площина панелі займається гадаю ( не завжди )
    // FIXME--low--: 06.12.2022 баг, при створенні і при ресайзі нижній рядок вилазить за рамки розміру ( не завжди )
    // FIXME--med--; 11.12.2022 негарно, виграшна лінія малюеться не гарно
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
    public boolean isNoEmpty(){
        return figura != null;
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
        if(isNoEmpty()) {
            group.getChildren().remove(figura.get());
            setFigura(figura.getName());
        }
    }

}

record MyPlayerRecord_just_for_fName(Figura.Name fig){
}

interface Lambda {
    ArrayList<Segment> run();
}