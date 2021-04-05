package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.ArtPiece;

public interface ArtPieceDAO {

	public int createListing(ArtPiece artPiece);
	public List<ArtPiece> getAllListings();
	
}
