package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.techelevator.dao.ArtPieceDAO;
import com.techelevator.model.ArtPiece;


@RestController
@CrossOrigin

public class ArtPieceController {

	private ArtPieceDAO artDAO;
	
	
	
	public ArtPieceController(ArtPieceDAO artDAO) {
		this.artDAO = artDAO;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/createListing", method=RequestMethod.POST)
	public void createListing( @RequestBody ArtPiece art){
		artDAO.createListing(art);
	}
	
	
	@RequestMapping(path = "/home", method=RequestMethod.GET)
	public List<ArtPiece> getAllListings(){
		return artDAO.getAllListings();
	}
	
	
	@RequestMapping(path = "/artDetails/{id}", method=RequestMethod.GET)
	public ArtPiece getListingByArtID(@PathVariable(name = "id") int artID) {
		
		return artDAO.getListingByArtID(artID);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
