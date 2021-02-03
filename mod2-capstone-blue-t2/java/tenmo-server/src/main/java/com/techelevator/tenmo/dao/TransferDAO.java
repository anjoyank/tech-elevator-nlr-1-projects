package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

public interface TransferDAO {
	
	public List<Transfer> transferHistory(int accountId);
	
	public Transfer viewTransferFromTransferId(int transferId);
	
	public Transfer createTransfer(int typeId, int statusId, int fromUserId, int toUserId, double amount);
	

}
