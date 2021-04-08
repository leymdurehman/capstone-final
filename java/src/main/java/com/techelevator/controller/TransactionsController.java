package com.techelevator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.TransactionsDao;
import com.techelevator.model.ArtPiece;
import com.techelevator.model.Transaction;


@RestController
@CrossOrigin

public class TransactionsController {
	
	private TransactionsDao dao;
	
	public TransactionsController(TransactionsDao dao) {
		this.dao = dao;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path="/orders", method=RequestMethod.POST)
	public void createTransaction(@RequestBody Transaction transaction) {
		dao.createTransaction(transaction);
	}
	
	

	
	
	
	
}
