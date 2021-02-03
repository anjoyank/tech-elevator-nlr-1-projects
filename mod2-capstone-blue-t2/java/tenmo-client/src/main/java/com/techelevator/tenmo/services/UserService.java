package com.techelevator.tenmo.services;

import java.util.Random;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;

public class UserService {

	private static String API_BASE_URL = "http://localhost:8080/";
	private static AuthenticatedUser currentUser;
	public static String AUTH_TOKEN = "";
	private AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

	private static RestTemplate restTemplate = new RestTemplate();

	public Account getAccountFromServer() {
		Account response = restTemplate
				.exchange(API_BASE_URL + "users/balance", HttpMethod.GET, makeAuthEntity(), Account.class).getBody();
		return response;
	}

	public User[] listUsers() {
		User[] userList;
		userList = restTemplate.exchange(API_BASE_URL + "users", HttpMethod.GET, makeAuthEntity(), User[].class)
				.getBody();
		return userList;
	}

	public Transfer[] getTransferHistory() {
		Transfer[] history = restTemplate
				.exchange(API_BASE_URL + "get-transfers", HttpMethod.GET, makeAuthEntity(), Transfer[].class)
				.getBody();
		return history;
	}

	public Transfer getTransferById() {
		Transfer transfer = restTemplate
				.exchange(API_BASE_URL + "transfers/{id}", HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
		return transfer;
	}

	public Transfer createATransfer(Transfer transfer) {
			transfer = restTemplate.exchange(API_BASE_URL + "new-transfer/", HttpMethod.POST,
					makeTransferEntity(transfer), Transfer.class).getBody();
			System.out.println("Transfer Complete!");
		return transfer;
	}

	public void transferFundsToUser(Account account, double amount) {
		Account newAccount = new Account();
		newAccount.setAccountId(account.getAccountId());
		newAccount.setUserId(account.getUserId());
		newAccount.setBalance(account.getBalance() + amount);
		account = restTemplate.exchange(API_BASE_URL + "approved-transfer/" + account.getAccountId(), HttpMethod.PUT,
				makeAccountEntity(account), Account.class).getBody();
	}

	public void transferFundsFromUser(Account account, double amount) {
		Account newAccount = new Account();
		newAccount.setAccountId(account.getAccountId());
		newAccount.setUserId(account.getUserId());
		newAccount.setBalance(account.getBalance() - amount);
		account = restTemplate.exchange(API_BASE_URL + "approved-transfer/" + account.getAccountId(), HttpMethod.PUT,
				makeAccountEntity(account), Account.class).getBody();
	}

	public void requestFundsFromUser(int fromUser, int toUser, double amount) {

		restTemplate.exchange(API_BASE_URL + "transfer-funds", HttpMethod.PUT, makeAuthEntity(), Transfer.class)
				.getBody();
	}

	private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
		return entity;
	}

	private HttpEntity<Account> makeAccountEntity(Account account) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity<Account> entity = new HttpEntity<>(account, headers);
		return entity;
	}

	private static HttpEntity makeAuthEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}


}
