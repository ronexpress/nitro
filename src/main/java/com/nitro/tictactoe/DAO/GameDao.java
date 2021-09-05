package com.nitro.tictactoe.DAO;

import com.nitro.tictactoe.modell.Score;

public interface GameDao {

    Score findByNames(String player1, String player2);

    void saveMatch(Score score);

}
