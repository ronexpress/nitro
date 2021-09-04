package com.nitro.tictactoe.modell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;


@Slf4j
@Data
@AllArgsConstructor
public class Board {

    private char[][] board = new char[3][3];

    public Board() {
        deleteBoard();
    }

    public void deleteBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean addMove(int i,int j,char playerSign){
        if(hasWinner() != ' ') {
            log.info("Not allow this move!");
            return false;
        }
        if(board[i][j] == ' ') {
            board[i][j] = playerSign;
            return true;
        }
        return false;
    }

    public char getPos(int i, int j){
        return board[i][j];
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    log.info(">is not Full");
                    return false;
                }
            }
        }
        log.info(">is Full!");
        return true;
    }

    public char hasWinner() {
        StringBuilder boardVector = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardVector.append(board[i][j]);
            }
            boardVector.append(',');
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardVector.append(board[j][i]);
            }
            boardVector.append(',');
        }
        for (int i = 0; i < 3; i++) {
            boardVector.append(board[i][i]);
        }
        boardVector.append(',');
        for (int i = 0; i < 3; i++) {
            boardVector.append(board[i][2-i]);
        }

        Pattern pattern = Pattern.compile("XXX");
        if(pattern.matcher(boardVector).find()){
            log.info(">Winner X");
            return 'X';
        }
        pattern = Pattern.compile("OOO");
        if(pattern.matcher(boardVector).find()){
            log.info(">Winner O");
            return 'O';
        }
        return ' ';
    }
}
