package com.nitro.tictactoe.DAO;

import com.nitro.tictactoe.modell.Score;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@NoArgsConstructor
public class GameMapper implements RowMapper<Score> {

    public Score mapRow(ResultSet rs, int rowNum) throws SQLException {
        Score score = new Score();
        System.out.println(rs.toString());
        score.setPlayer1(rs.getString("pl1_name"));
        score.setPlayer2(rs.getString("pl2_name"));
        score.setPl1Win(rs.getInt("pl1_win"));
        score.setPl2Win(rs.getInt("pl2_win"));
        score.setTie(rs.getInt("tie"));
        return score;
    }

}
