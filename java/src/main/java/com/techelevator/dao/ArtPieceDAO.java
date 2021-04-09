package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.ArtPiece;

public interface ArtPieceDAO {

	public int createListing(ArtPiece artPiece);
	public List<ArtPiece> getAllListings();
	public ArtPiece getListingByArtID(int artID);
	public void updateArtPiece(ArtPiece updatedArtPiece);
	public void deleteArtPiece(int artID);
	public void overrideFees(ArtPiece art);
	
}
