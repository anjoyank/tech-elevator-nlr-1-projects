package com.techelevator.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.techelevator.model.Game;

public interface GameDAO {
	
	List<Game> findAll();
	
	Game getGameById(int gameId);

    Game findByGameName(String gameName);

    int findIdByGameName(String gameName);

    void create(String gameName, String status, LocalDate startDate, LocalDate endDate, int organizerId);

}
