package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

@Component
public class TransferSqlDAO implements TransferDAO {

	private JdbcTemplate jdbcTemplate;

	public TransferSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Transfer> transferHistory(int accountId) {
		List<Transfer> transferList = new ArrayList<Transfer>();
		String sql = "SELECT * FROM transfers WHERE account_to = ? OR account_from = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId, accountId);
		while (result.next()) {
			int transferId = result.getInt("transfer_id");
			int typeId = result.getInt("transfer_type_id");
			int statusId = result.getInt("transfer_status_id");
			int accountFrom = result.getInt("account_from");
			int accountTo = result.getInt("account_to");
			double amount = result.getDouble("amount");
			Transfer transfer = new Transfer(transferId, typeId, statusId, accountFrom, accountTo, amount);
			transferList.add(transfer);
		}
		return transferList;
	}

	@Override
	public Transfer viewTransferFromTransferId(int id) {
		Transfer transfer = null;
		String sql = "SELECT * FROM transfers WHERE transfer_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
		while (result.next()) {
			int transferId = result.getInt("transfer_id");
			int typeId = result.getInt("transfer_type_id");
			int statusId = result.getInt("transfer_status_id");
			int accountFrom = result.getInt("account_from");
			int accountTo = result.getInt("account_to");
			double amount = result.getDouble("amount");
			transfer = new Transfer(transferId, typeId, statusId, accountFrom, accountTo, amount);
		}
		return transfer;
	}

	@Override
	public Transfer createTransfer(int typeId, int statusId, int accountFrom, int accountTo, double amount) {
		String sql = "INSERT INTO transfers (transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?,?,?,?,?)";
		//String sql2 = "SELECT transfer_id FROM transfers ORDER BY transfer_id DESC LIMIT 1";
		String sql2 = "UPDATE accounts SET balance = ? WHERE account_id = ?";
		
		Transfer transfer = new Transfer(0, typeId, statusId, accountFrom, accountTo, amount);
		jdbcTemplate.update(sql, typeId, statusId, accountFrom, accountTo, amount);
		
		AccountsSqlDAO withdrawDAO = new AccountsSqlDAO(jdbcTemplate);
		double withdrawBalance = withdrawDAO.getBalance(accountFrom) - amount;
		jdbcTemplate.update(sql2, withdrawBalance, accountFrom);
		
		AccountsSqlDAO depositDAO = new AccountsSqlDAO(jdbcTemplate);
		double depositBalance = depositDAO.getBalance(accountTo) + amount;
		jdbcTemplate.update(sql2, depositBalance, accountTo);
		
		
		return transfer;
	}

}
