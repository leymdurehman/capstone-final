package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Fee;
import com.techelevator.model.Transaction;

public interface TransactionsDao {

	
	public int createTransaction(Transaction transaction);
	public void setFees(Fee newFees);
	public Fee getFees();

}
