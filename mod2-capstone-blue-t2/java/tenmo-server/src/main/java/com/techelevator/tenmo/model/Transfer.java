package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;

public class Transfer {

	private int transferId;
	@NotBlank(message = "Must have account to transfer from.")
	private int accountFrom;
	@NotBlank(message = "Must have account to transfer to.")
	private int accountTo;
	private int typeId;
	private int statusId;
	private double amount;
	private String usernameFrom = "";
	private String usernameTo = "";
	
	public Transfer () {}

	public Transfer(int transferId, int accountFrom, int accountTo, int typeId, int statusId, double amount) {
		this.transferId = transferId;
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.typeId = typeId;
		this.statusId = statusId;
		this.amount = amount;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}

	public int getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getUsernameFrom() {
		return usernameFrom;
	}

	public void setUsernameFrom(String usernameFrom) {
		this.usernameFrom = usernameFrom;
	}

	public String getUsernameTo() {
		return usernameTo;
	}

	public void setUsernameTo(String usernameTo) {
		this.usernameTo = usernameTo;
	}

}
