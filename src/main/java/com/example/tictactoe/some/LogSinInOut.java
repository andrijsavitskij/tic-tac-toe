package com.example.tictactoe.some;

import com.example.tictactoe.HelloApplication;
import com.example.tictactoe.Settings;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class LogSinInOut {
    private static Status status = Status.not_working;

    public enum Status{ not_working,work_but_no_module, logIn, singIn, profile}
    private static Pane pane = new Pane();

    private static void create_logIn() {
        clear();
        try {
            pane.getChildren().add(new FXMLLoader(HelloApplication.class.getResource("login-view.fxml")).load());
            status = Status.logIn;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private static void create_singIn() {
        clear();
        try {
            pane.getChildren().add(new FXMLLoader(HelloApplication.class.getResource("singin-view.fxml")).load());
            status = Status.singIn;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void create_profile() {
        clear();
        try {
            pane.getChildren().add(new FXMLLoader(HelloApplication.class.getResource("profile-view.fxml")).load());
            status = Status.profile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public boolean player_logIn(Player player){
//        // must be login fun
//        if(player.name().equals("admin") /* don't have DB with password(( && player.getPassword() == "1111"*/){
//            Settings.player = player;
//            return true;
//        }else return false;
//    }
//
//    public boolean player_singIn(Player player){
//        // must be singIn fun
//        // if DB.get(player.name()) == already exist : return false;
//        return true;
//    }

    /**
     * Працює без перевірок користувача, тому потрібна окрема перевірка
     */
    public static void setNewStatus(Status status1){
        switch (status1){
            case not_working -> stop();
            case work_but_no_module -> clear();
            case logIn -> create_logIn();
            case singIn -> create_singIn();
            case profile -> create_profile();
            default -> throw new RuntimeException();
        }
    }

    public static void setPane(Pane pane){

        LogSinInOut.pane = pane;
    }
    public static void start(){
        if(Objects.equals(Settings.player.name, "Player")){
            create_logIn();
        }
        else{
            create_profile();
        }
    }
    private static void clear(){
        pane.getChildren().clear();// not safe
        status = Status.work_but_no_module;
    }
    private static void stop(){
        //pane.getParent().getChildrenUnmodifiable().remove(pane);
        pane.getChildren().clear();// not safe
        pane = new Pane();
        status = Status.not_working;
    }

    public static boolean isNotWork(){
        return status == Status.not_working;
    }
    public static boolean isNoModule(){
        return status == Status.work_but_no_module;
    }
    public static boolean isLogin(){
        return status == Status.logIn;
    }
    public static boolean isSingIn(){
        return status == Status.singIn;
    }
    public static boolean isProfile(){
        return status == Status.profile;
    }

}
