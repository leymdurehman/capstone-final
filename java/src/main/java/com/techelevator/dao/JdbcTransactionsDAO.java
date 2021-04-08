package com.techelevator.dao;

import java.time.LocalDate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.ArtPiece;
import com.techelevator.model.Transaction;

@Component
public class JdbcTransactionsDAO implements TransactionsDao{

	
	
	private JdbcTemplate jdbcTemplate; 
	public JdbcTransactionsDAO (JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		
	}

	
	public int createTransaction(Transaction transaction) {
		
		sellArt(transaction.getArtID());
		
		LocalDate dateSold = LocalDate.now();
		
		
		String sql = "INSERT INTO transactions (order_id, customer_id, art_id, date_of_sale, fee, commission, total_price) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING order_id";
		
		return jdbcTemplate.queryForObject(sql, int.class, transaction.getCustomerId(), transaction.getArtID(), dateSold, transaction.getFee(), transaction.getCommission(), transaction.getTotalPrice());
		
	}
	
	
	private void sellArt(int artID) {
		
		String sql = "UPDATE art_pieces SET is_sold = true WHERE art_id = ?";
		
		jdbcTemplate.update(sql, artID);	
		
	}
	
	
	
	
	
	
	
	
	
}
