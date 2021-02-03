package com.techelevator.tenmo.dao;

import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Account;

@Component
public interface AccountsDAO {
	
	public double getBalance(int userId);
	
	public Account getAccount(int userId);
	
	public void transferFunds(Account account);

}
