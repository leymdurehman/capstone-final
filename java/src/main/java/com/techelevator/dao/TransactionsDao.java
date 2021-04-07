package com.techelevator.dao;

import com.techelevator.model.ArtPiece;

public interface TransactionsDao {

	
	public int createTransaction(ArtPiece artpiece, String username);
}
