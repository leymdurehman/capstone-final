package com.techelevator.controller;

import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	
	@RequestMapping(path = "/createListing", method=RequestMethod.POST)
	public void createListing( @RequestBody ArtPiece art){
		
		artDAO.createListing(art);
		

	}
	
	
	@RequestMapping(path = "/home", method=RequestMethod.GET)
	public List<ArtPiece> getAllListings(){
			
		return artDAO.getAllListings();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
