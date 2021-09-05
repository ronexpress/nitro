package com.nitro.tictactoe.modell;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Game {

    private Player player1;
    private Player player2;
    private GameStatus status;
    private Board board;
    private Score score;
    private int actualPlayer;

    public Game(Player player1, Player player2, GameStatus status, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        this.score.setPlayer1(player1.getName());
        this.score.setPlayer1(player1.getName());
        this.status = status;
        this.board = board;
    }

    public int changePlayer() {
        actualPlayer = 1 - actualPlayer;
        return actualPlayer;
    }
}
