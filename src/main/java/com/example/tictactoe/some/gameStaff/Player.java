package com.example.tictactoe.some.gameStaff;

public record Player(String name, int winCount, long id) {
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", winCount=" + winCount +
                ", id=" + id +
                '}';
    }

}
