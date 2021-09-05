package com.nitro.tictactoe.modell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    private String player1;
    private String player2;
    private int pl1Win;
    private int pl2Win;
    private int tie;
}
