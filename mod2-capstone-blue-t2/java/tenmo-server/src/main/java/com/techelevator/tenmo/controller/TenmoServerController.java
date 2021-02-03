package com.techelevator.tenmo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

@RestController
@PreAuthorize("isAuthenticated()")
public class TenmoServerController {

	private TransferDAO transferDao;
	private UserDAO userDao;
	private AccountsDAO accountsDao;

	public TenmoServerController(TransferDAO transferDao, UserDAO userDao, AccountsDAO accountsDao) {
		this.accountsDao = accountsDao;
		this.transferDao = transferDao;
		this.userDao = userDao;
	}

	// Finds all users
	@RequestMapping(path = "users", method = RequestMethod.GET)
	public List<User> listAllUsers() {

		return userDao.findAll();

	}

	// Finds the information of the user that logs into the application
	@RequestMapping(path = "users/user", method = RequestMethod.GET)
	public User findUserByUsername(Principal principal) {
		String username = principal.getName();
		return userDao.findByUsername(username);
	}
	
	

	// Returns the Account Id, User Id, and balance of the user that is currently logged in
	@RequestMapping(path = "users/balance", method = RequestMethod.GET)
	public Account findUserAccount(Principal principal) {
		String username = principal.getName();
		int userId = userDao.findIdByUsername(username);

		Account account = new Account(-1, userId, accountsDao.getBalance(userId));
		return account;
	}

	// Creates a new transfer
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "new-transfer", method = RequestMethod.POST)
	public Transfer sendTransfer(@RequestBody Transfer transfer) {
		//return transferDao.createTransfer(2, 2, 0, 0, 0);
		return transfer = transferDao.createTransfer(transfer.getTypeId(), transfer.getStatusId(),transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
	}
	
	// Accepts a transfer that was created
	@ResponseStatus(HttpStatus.ACCEPTED)
	@RequestMapping(path = "approved-transfer/{userTo}", method = RequestMethod.PUT)
	public void transferTEBucks(@RequestBody Account account, @PathVariable int userTo) {
		
	}

	// Should return a transfer based on the transfer ID
	@RequestMapping(path = "transfers/{transferId}", method = RequestMethod.GET)
	public Transfer findTransferByTransferId(@PathVariable int transferId) {
		return transferDao.viewTransferFromTransferId(transferId);
	}

	// Should return all of the transfers based on User ID
	@RequestMapping(path = "get-transfers", method = RequestMethod.GET)
	public List<Transfer> getTransfersByUserId(Principal principal) {
		int userId = userDao.findIdByUsername(principal.getName());
		List <Transfer> listOfTransfers = transferDao.transferHistory(userId);
		
		// Need to figure out how to make this work
		
		return listOfTransfers;
		
	}

}
