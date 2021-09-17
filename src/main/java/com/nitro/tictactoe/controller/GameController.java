package com.nitro.tictactoe.controller;

import com.nitro.tictactoe.modell.Game;
import com.nitro.tictactoe.modell.Player;
import com.nitro.tictactoe.service.GameService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    @ApiOperation(value = "Start Game", notes = "This method start a new game, create/load old data. Type = [ HUMAN | AI ]")
    @PostMapping("/start")
    public ResponseEntity<Game> startGame(@RequestBody List<Player> players) {
        log.info(">Start GAME, set players: " + players.get(0) + " - " + players.get(1));
        return ResponseEntity.ok(gameService.createGame(players.get(0), players.get(1)));
    }

    @ApiOperation(value = "Make a move", notes = "This method stores the move and return the game data. It also makes an Ai move.")
    @PostMapping("/move")
    public ResponseEntity<Game> makeMove(@RequestBody int[] coor) {
        log.info(">Make move: " + coor[0] + " - " + coor[1]);
        gameService.makeMove(coor[0], coor[1]);
        return ResponseEntity.ok(gameService.makeAiMove());
    }
}
