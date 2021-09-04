package com.nitro.tictactoe.service;

import com.nitro.tictactoe.modell.Board;
import com.nitro.tictactoe.modell.Game;
import com.nitro.tictactoe.modell.Player;
import com.nitro.tictactoe.modell.GameStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class GameService {

    private Game game;
    private boolean ready = false;

    public Game createGame(Player player1, Player player2) {
        Game game = new Game();
        this.game = game;
        game.setBoard(new Board());
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        ready = true;
        getStatus();
        return game;
    }

    public GameStatus getStatus() {
        GameStatus gameStatus = new GameStatus();
        gameStatus.setBoardFull(game.getBoard().isFull());
        gameStatus.setHasWinner(game.getBoard().hasWinner() != ' ');
        gameStatus.setWinner(game.getBoard().hasWinner());
        ready = (!gameStatus.isBoardFull() && !gameStatus.isHasWinner());
        gameStatus.setReady(ready);
        game.setStatus(gameStatus);
        return gameStatus;
    }

    public Board getBoard() {
        return game.getBoard();
    }

    public List<Player> getPlayers() {
        return new ArrayList<Player>(List.of(game.getPlayer1(), game.getPlayer2()));
    }

    public Game makeMove(int i, int j) {
        char[] tictac = new char[]{'X', 'O'};
        if (game.getBoard().addMove(i, j, tictac[game.getActualPlayer()])) {
            game.changePlayer();
        }
        log.info(">READY:" + game.getStatus().isReady());
        countWins();
        getStatus();

        return game;
    }

    public void countWins() {
        if (game.getStatus().isReady()) {
            if (game.getBoard().hasWinner() != ' ') {
                if (game.getBoard().hasWinner() == 'X') {
                    game.getPlayer1().setWin(game.getPlayer1().getWin() + 1);
                } else {
                    game.getPlayer2().setWin(game.getPlayer2().getWin() + 1);
                }
                game.getStatus().setReady(false);
            }else{
                if (game.getBoard().isFull()) {
                    game.getPlayer1().setTie(game.getPlayer1().getTie() + 1);
                    game.getPlayer2().setTie(game.getPlayer2().getTie() + 1);
                    game.getStatus().setReady(false);
                }
            }
        }
        log.info("------" + game.getActualPlayer() + " " + game.getPlayer1() + "/" + game.getPlayer2());
    }
}
