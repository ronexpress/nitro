package com.nitro.tictactoe.modell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private String name;
    private PlayerType type;

    @JsonIgnore
    private Board board;
    @JsonIgnore
    private StringBuilder boardVector = new StringBuilder();

    public Player(String name, PlayerType type, Board board) {
        this.name = name;
        this.type = type;
        this.board = board;
    }

    public void nextMove(Board board) {
        this.board = board;
        if (winnerMove()) return;
        if (defenseMove()) return;
        if (bestMove()) return;
        randomMove();
    }

    private boolean winnerMove() {

        return false;
    }

    private boolean defenseMove() {

        return false;
    }

    private boolean bestMove() {

        return false;
    }

    private boolean randomMove() {
        if (board.isFull()) return false;
        Random random = new Random();
        int x;
        int y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (board.getPos(x, y) != ' ');
        return true;
    }


    public void makeBoardVector() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardVector.append(board.getPos(i, j));
            }
            boardVector.append(',');
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardVector.append(board.getPos(j, i));
            }
            boardVector.append(',');
        }
        for (int i = 0; i < 3; i++) {
            boardVector.append(board.getPos(i, i));
        }
        boardVector.append(',');
        for (int i = 0; i < 3; i++) {
            boardVector.append(board.getPos(i, 2 - i));
        }
    }
}
