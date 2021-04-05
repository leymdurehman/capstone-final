package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.ArtPieceDAO;
import com.techelevator.model.ArtPiece;


@RestController
@CrossOrigin
public class ArtPieceController {

	private ArtPieceDAO dao;
	
	
	
	public ArtPieceController(ArtPieceDAO artDAO) {
		this.dao = artDAO;
		
	}
	
	
	@RequestMapping(path = "/createListing", method=RequestMethod.POST)
	public void createListing( @RequestBody ArtPiece art){
		
		dao.createListing(art);
		

	}
	
	
	@RequestMapping(path = "/home", method=RequestMethod.GET)
	public List<ArtPiece> getAllListings(){
			
		return dao.getAllListings();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
