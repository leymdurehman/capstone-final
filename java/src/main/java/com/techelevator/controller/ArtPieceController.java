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
	
	
	@RequestMapping(path = "/artDetails/{artId}", method=RequestMethod.GET)
	public ArtPiece getListingByArtID(@PathVariable int artId) {
		
		return artDAO.getListingByArtID(artId);
	}
	
	@RequestMapping(path="/edit", method=RequestMethod.PUT)
	public void updateArtPiece(@RequestBody ArtPiece art) {
		
		artDAO.updateArtPiece(art);
	}
	
    @ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path="/delete/{id}", method=RequestMethod.DELETE)
	public void deleteArtPiece(@PathVariable int id) {
		
		artDAO.deleteArtPiece(id);
	}

	
}
