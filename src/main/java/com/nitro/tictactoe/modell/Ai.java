package com.nitro.tictactoe.modell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ai {

    private char playerSign;
    private char oppSign;
    private Board board;
    private StringBuilder boardVector = new StringBuilder();
    private int[] ret = {0, 0};
    private char[] signs = {'X', 'O'};

    public Ai(int actualPlayer, Board board) {
        this.playerSign = signs[actualPlayer];
        this.oppSign = signs[1 - actualPlayer];
        this.board = board;
    }

    public int[] nextMove() {
        makeBoardVector();
        if (winnerMove(playerSign)) return ret;
        if (defenseMove()) return ret;
        if (bestMove()) return ret;
        randomMove();
        return ret;
    }

    private boolean winnerMove(char sign) {
        Pattern pattern = Pattern.compile(" " + sign + sign + "|" + sign + " " + sign + "|" + sign + sign + " ");
        Matcher matcher = pattern.matcher(boardVector);
        if (matcher.find()) {
            int pos = matcher.start();
            if (pos < 12) {
                ret[0] = (int) pos / 4;
                ret[1] = matcher.group().indexOf(" ");
                return true;
            }
            if (pos < 24) {
                ret[0] = matcher.group().indexOf(" ");
                ret[1] = (int) (pos - 12) / 4;
                return true;
            }
            if (pos == 24) {
                ret[0] = matcher.group().indexOf(" ");
                ret[1] = matcher.group().indexOf(" ");
                return true;
            }
            if (pos == 28) {
                ret[0] = matcher.group().indexOf(" ");
                ret[1] = 2 - matcher.group().indexOf(" ");
                return true;
            }
        }
        return false;
    }

    private boolean defenseMove() {
        return winnerMove(oppSign);
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
        ret[0] = x;
        ret[1] = y;
        return true;
    }


    public void makeBoardVector() {
        boardVector.delete(0, 100);
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
