package com.techelevator.tenmo;

import java.util.List;
import java.util.Scanner;

import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.AuthenticationServiceException;
import com.techelevator.tenmo.services.UserService;
import com.techelevator.view.ConsoleService;

public class App {

	private static final String API_BASE_URL = "http://localhost:8080/";

	private static final String MENU_OPTION_EXIT = "Exit";
	private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN,
			MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
	private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
	private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS,
			MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS,
			MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };

	private AuthenticatedUser currentUser;
	private ConsoleService console;
	private AuthenticationService authenticationService;
	private UserService userService = new UserService();

	public static void main(String[] args) {
		App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL));
		app.run();
	}

	public App(ConsoleService console, AuthenticationService authenticationService) {
		this.console = console;
		this.authenticationService = authenticationService;
	}

	public void run() {
		System.out.println("*********************");
		System.out.println("* Welcome to TEnmo! *");
		System.out.println("*********************");

		registerAndLogin();
		mainMenu();
	}

	private void mainMenu() {
		while (true) {
			String choice = (String) console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
				viewCurrentBalance();
			} else if (MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
				viewTransferHistory();
			} else if (MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
				viewPendingRequests();
			} else if (MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
				sendBucks();
			} else if (MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
				requestBucks();
			} else if (MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void viewCurrentBalance() {
		Account account = userService.getAccountFromServer();
		System.out.println("Your current account balance is: $" + account.getBalance());
	}

	private void viewTransferHistory() {
		Transfer[] transferHistoryLog = userService.getTransferHistory();
		for (Transfer transferLog : transferHistoryLog) {
			transferDetails(transferLog);
			
		}
		
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub

	}

	private void sendBucks() {

		User[] userList = userService.listUsers();
		// Should we create a list of integers that houses all of the user IDs?
		Scanner scanner = new Scanner(System.in);
		User loggedInUser = currentUser.getUser();
		int loggedInUserId = loggedInUser.getId();

		System.out.println("-------------------------------------------");
		System.out.println("Users");
		System.out.println("ID" + "\t" + "\t" + " Name");
		System.out.println("-------------------------------------------");
		// for each in userlist
		for (User user : userList) {
			System.out.println(user.getId() + "\t" + "\t" + user.getUsername());
		}
		System.out.println("---------");
		System.out.println("\n" + "Enter ID of user you are sending to (0 to cancel): ");

		String input = scanner.nextLine();
		int inputInt = Integer.parseInt(input);
		if (inputInt == 0) {
			exitProgram();
		}

		if (inputInt != 0) {
			System.out.println("Enter amount: ");
			double transferAmount = Double.parseDouble(scanner.nextLine());

			if (userService.getAccountFromServer().getBalance() >= transferAmount) {
				double newBalance = userService.getAccountFromServer().getBalance();
				Transfer transfer = new Transfer();
				transfer.setAccountFrom(loggedInUserId);
				transfer.setAccountTo(inputInt);
				transfer.setAmount(transferAmount);
				transfer.setStatusId(2);
				transfer.setTypeId(2);

				Account newAccountFrom = new Account();
				newAccountFrom.setAccountId(loggedInUserId);
				newAccountFrom.setBalance(newBalance);
				newAccountFrom.setUserId(loggedInUserId);
			
			
				userService.createATransfer(transfer);
				userService.transferFundsFromUser(newAccountFrom, transferAmount);
			}
		}

	}

	private void requestBucks() {
		// TODO Auto-generated method stub

		User[] userList = userService.listUsers();
		Scanner scanner = new Scanner(System.in);
		int loggedInUserId = 0;
		User loggedInUser = currentUser.getUser();
		loggedInUserId = loggedInUser.getId();

		System.out.println("-------------------------------------------");
		System.out.println("Users");
		System.out.println("ID" + "\t" + "\t" + " Name");
		System.out.println("-------------------------------------------");
		for (User user : userList) {
			System.out.println(user.getId() + "\t" + "\t" + user.getUsername());
		}
		System.out.println("---------");
		System.out.println("\n" + "Enter ID of user you are requesting from (0 to cancel): ");

		String input = scanner.nextLine();
		int inputInt = Integer.parseInt(input);
		if (inputInt == 0) {
			exitProgram();
		}

		if (inputInt != 0) {

			System.out.println("Enter amount: ");
			double requestAmount = Double.parseDouble(scanner.nextLine());
			if (userService.getAccountFromServer().getBalance() <= requestAmount) {

				Transfer transfer = new Transfer();
				transfer.setAccountFrom(inputInt);
				transfer.setAccountTo(currentUser.getUser().getId());
				transfer.setAmount(requestAmount);
				transfer.setStatusId(2);
				transfer.setTypeId(2);

				userService.createATransfer(transfer);
				userService.requestFundsFromUser(inputInt, currentUser.getUser().getId(), requestAmount);

			}

		}

	}

	private void exitProgram() {
		System.exit(0);
	}

	public void transferDetails(Transfer transfer) {
		User[] userList = userService.listUsers();
		String toUsername = "";
		String fromUsername = currentUser.getUser().getUsername();
		for (User user: userList) {
			if (user.getId() == transfer.getAccountTo()) {
				toUsername = user.getUsername();
			}
		}
		
		
		System.out.println("-------------------------------------------");
		System.out.println("Transfer Details");
		System.out.println("-------------------------------------------");
		System.out.println("ID: " + transfer.getTransferId());
		System.out.println("From: " + fromUsername);
		System.out.println("To: " + toUsername);
		System.out.println("Type: Send");
		System.out.println("Status: Approved");
		System.out.println("Amount: $" + transfer.getAmount());
	}

	private void registerAndLogin() {
		while (!isAuthenticated()) {
			String choice = (String) console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	private boolean isAuthenticated() {
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
		while (!isRegistered) // will keep looping until user is registered
		{
			UserCredentials credentials = collectUserCredentials();
			try {
				authenticationService.register(credentials);
				isRegistered = true;
				System.out.println("Registration successful. You can now login.");
			} catch (AuthenticationServiceException e) {
				System.out.println("REGISTRATION ERROR: " + e.getMessage());
				System.out.println("Please attempt to register again.");
			}
		}
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) // will keep looping until user is logged in
		{
			UserCredentials credentials = collectUserCredentials();
			try {
				currentUser = authenticationService.login(credentials);
				String currentToken = currentUser.getToken();
				UserService.AUTH_TOKEN = currentToken;
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: " + e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}

	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
}
