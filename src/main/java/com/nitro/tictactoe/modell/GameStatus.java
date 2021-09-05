package com.nitro.tictactoe.modell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameStatus {

    private boolean ready;
    private boolean boardFull;
    private boolean hasWinner;
    private char winner = ' ';
}
