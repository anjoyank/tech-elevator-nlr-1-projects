package com.techelevator.model;

import java.time.LocalDate;
import java.util.Date;

public class Game {
	
	private int gameId;
	private String gameName;
	private String status;
	private LocalDate startDate;
	private LocalDate endDate;
	private int organizerId;
	
	public Game() {};
	
	public Game(int gameId, String gameName, String status, LocalDate startDate, LocalDate endDate, int organizerId) {
		this.gameId = gameId;
		this.gameName = gameName;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.organizerId = organizerId;
	}

	public int getGameId() {
		return gameId;
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getOrganizerId() {
		return organizerId;
	}
	
	public void setOrganizerId(int organizerId) {
		this.organizerId = organizerId;
	}
	
	

}
