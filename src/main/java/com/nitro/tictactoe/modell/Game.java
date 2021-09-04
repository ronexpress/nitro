package com.nitro.tictactoe.modell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
public class Game {

    private Player player1;
    private Player player2;
    private GameStatus status;
    private Board board;
    private int actualPlayer;

    public Game(Player player1, Player player2, GameStatus status, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        this.status = status;
        this.board = board;
    }

    public int changePlayer() {
        actualPlayer = 1 - actualPlayer;
        return actualPlayer;
    }
}
