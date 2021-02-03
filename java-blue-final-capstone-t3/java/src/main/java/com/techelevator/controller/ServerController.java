package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.GameDAO;
import com.techelevator.model.Game;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class ServerController {
	@Autowired
	private GameDAO gameDAO;

	public ServerController(GameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path= "/games", method= RequestMethod.POST) 
		public void addGame(@Valid @RequestBody Game game) {
			 gameDAO.create(game.getGameName(), game.getStatus(), game.getStartDate(), game.getEndDate(), game.getOrganizerId());
	}
	
	@RequestMapping(path="/games", method=RequestMethod.GET)
	public List<Game> getAllGames() {
		List<Game> allGames = gameDAO.findAll();
		return allGames;
	}
}	
