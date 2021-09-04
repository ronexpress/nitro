package com.nitro.tictactoe.controller;

import com.nitro.tictactoe.modell.Board;
import com.nitro.tictactoe.modell.Game;
import com.nitro.tictactoe.modell.Player;
import com.nitro.tictactoe.modell.GameStatus;
import com.nitro.tictactoe.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;


    @GetMapping("/")
    public String getHello() {
        log.info(">getHello()");
        return "Hello World!";
    }

    @PostMapping("/start")
    public ResponseEntity<Game> startGame(@RequestBody List<Player> players) {
        log.info(">Start GAME, set players: " + players.get(0) + " - " + players.get(1));
        return ResponseEntity.ok(gameService.createGame(players.get(0), players.get(1)));
    }

    @GetMapping("/status")
    public GameStatus getStatus() {
        log.info(">getStatus()");
        return gameService.getStatus();
    }

    //TODO delete
    @GetMapping("/board")
    public Board getBoard() {
        log.info(">getBoard()");
        return gameService.getBoard();
    }

    //TODO delete
    @GetMapping("/players")
    public List<Player> getPlayers() {
        log.info(">getPlayers()");
        return gameService.getPlayers();
    }

    @PostMapping("/move")
    public ResponseEntity<Game> makeMove(@RequestBody int[] coor) {
        log.info(">Make move: " + coor[0] + " - " + coor[1]);
        return ResponseEntity.ok(gameService.makeMove(coor[0],coor[1]));
    }
}
