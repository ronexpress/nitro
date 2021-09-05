package com.nitro.tictactoe.DAO;

import com.nitro.tictactoe.modell.Player;
import com.nitro.tictactoe.modell.Score;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
public class GameDaoJdbc implements GameDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Score findByNames(String player1, String player2) {
        String sql = "SELECT * FROM game WHERE pl1_name = ? AND pl2_name = ?";
        Score score;
        try {
            score = jdbcTemplate.queryForObject(sql, new GameMapper(), player1, player2);
        } catch (DataAccessException e) {
            score = new Score(player1, player2, 0, 0, 0);
            sql = "INSERT INTO game (pl1_name, pl2_name, pl1_win, pl2_win, tie) VALUES (?, ?, 0, 0, 0)";
            jdbcTemplate.update(sql, player1, player2);
        }
        log.info(">LoadScore:" + score.toString());
        return score;
    }

    @Override
    public void saveMatch(Score score) {
        String sql = "UPDATE game SET pl1_win = ?, pl2_win = ?, tie = ? WHERE pl1_name = ? AND pl2_name = ?";
        jdbcTemplate.update(sql, score.getPl1Win(), score.getPl2Win(), score.getTie(), score.getPlayer1(), score.getPlayer2());
        log.info(">SaveScore:" + score.toString());
    }
}
