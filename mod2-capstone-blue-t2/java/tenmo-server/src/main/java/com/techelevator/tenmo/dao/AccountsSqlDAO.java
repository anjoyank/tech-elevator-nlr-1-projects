package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;

@Component
public class AccountsSqlDAO implements AccountsDAO {

	private AccountsDAO accountsDao;
	private JdbcTemplate jdbcTemplate;

	public AccountsSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public double getBalance(int userId) {
		double balance = 0;

		String sql = "SELECT balance FROM accounts WHERE account_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
		while (result.next()) {
			balance = result.getDouble("balance");
		}
		return balance;
	}

	
	public void updateAccount(Account account) {
		//double fromBalance = account.getBalance();
		//double toBalance = account.getBalance();
		//double transferAmount = account.;
		
		String sqlUpdateAccount = "UPDATE accounts SET balance = ? WHERE account_id = ?";
		jdbcTemplate.update(sqlUpdateAccount, account.getBalance(), account.getAccountId());
		
		}
	

	@Override
	public Account getAccount(int userId) {
		
		Account account = new Account();
		String sql = "SELECT * FROM accounts WHERE user_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
		
		while (result.next()) {
				int id = result.getInt("user_Id");
				int accId = result.getInt("account_id");
				double accountBalance = result.getDouble("balance");
				
				account.setBalance(accountBalance);
				account.setAccountId(accId);
				account.setUserId(id);
				
			}
		
		return account;
	}

	@Override
	public void transferFunds(Account account) {
		// TODO Auto-generated method stub
		
	}

}
