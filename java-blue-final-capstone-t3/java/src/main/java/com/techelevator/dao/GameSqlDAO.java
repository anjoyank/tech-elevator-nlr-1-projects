package com.techelevator.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.techelevator.model.Game;
import com.techelevator.model.User;


@Component
public class GameSqlDAO implements GameDAO {
	
	private JdbcTemplate jdbcTemplate;

	public GameSqlDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Game> findAll() {
		List<Game> games = new ArrayList<>();
        String sql = "SELECT * from games";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Game game = mapRowToGame(results);
            games.add(game);
        }
        return games;
	}

	@Override
	public Game getGameById(int gameId) {
		String sql = "SELECT * FROM games WHERE game_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
		if(results.next()) {
			return mapRowToGame(results);
		} else {
			throw new RuntimeException("gameId "+gameId+" was not found.");
		}
	}

	@Override
	public Game findByGameName(String gameName) {
		Game newGame = new Game();
		for (Game game : this.findAll()) {
            if( game.getGameName().toLowerCase().equals(gameName.toLowerCase())) {
                newGame = game;
            }
        }
		return newGame;
	}

	@Override
	public int findIdByGameName(String gameName) {
		return jdbcTemplate.queryForObject("SELECT game_id FROM games WHERE game_name = ?", int.class, gameName);
	}

	@Override
	public void create(String gameName, String status, LocalDate startDate, LocalDate endDate, int organizerId) {
		String sql = "INSERT INTO games (game_name, status, start_date, end_date, organizer_id) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sql, gameName, status, startDate, endDate, organizerId);
	}
	
	private Game mapRowToGame(SqlRowSet rs) {
        Game game = new Game();
        game.setGameId(rs.getInt("game_id"));
        game.setGameName(rs.getString("game_name"));
        game.setStatus(rs.getString("status"));
        game.setStartDate(rs.getDate("start_date").toLocalDate());
        game.setEndDate(rs.getDate("end_date").toLocalDate());
        game.setOrganizerId(rs.getInt("organizer_id"));
        return game;
    }

}
