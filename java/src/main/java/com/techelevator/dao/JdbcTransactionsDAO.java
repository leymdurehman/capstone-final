package com.techelevator.dao;

import java.time.LocalDate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.ArtPiece;

public class JdbcTransactionsDAO {

	
	
	private JdbcTemplate jdbcTemplate; 
	public JdbcTransactionsDAO (JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		
	}
	
	
	// create transaction - insert 
	
	public int createTransaction(ArtPiece artpiece, String username) {
		
		LocalDate dateSold = LocalDate.now();
		
		int customerID = getCustomerIDFromUsername(username);
		
		
		String sql = "INSERT INTO transactions (order_id, customer_id, art_id, date_of_sale) VALUES (default, ?, ?, ?) returning order_id";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, customerID, artpiece.getArtID(), dateSold);
		
		
		int orderID = 0;
		if(result.next()) {
			orderID = result.getInt("order_id");
		}
		return orderID;
		}
	
	
	public int getCustomerIDFromUsername(String username) {
		
		String sql ="Select customer_id FROM customer\n" + 
				"JOIN users on users.user_id = customer.user_id\n" + 
				"WHERE username = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
		
		int customerID = 0;
		if(result.next()) {
			
			customerID = result.getInt("customer_id");
		}
		
			return customerID;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
