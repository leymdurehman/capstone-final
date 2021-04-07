package com.techelevator.dao;

import java.time.LocalDate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.ArtPiece;
import com.techelevator.model.Transactions;

@Component
public class JdbcTransactionsDAO implements TransactionsDao{

	
	
	private JdbcTemplate jdbcTemplate; 
	public JdbcTransactionsDAO (JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		
	}
	
	
	// create transaction - insert 
	
	public int createTransaction(Transactions transaction) {
		
		LocalDate dateSold = LocalDate.now();
		
		
		String sql = "INSERT INTO transactions (order_id, customer_id, art_id, date_of_sale) VALUES (default, ?, ?, ?) returning order_id";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transaction.getCustomerId(), transaction.getArtID(), dateSold);
		
		
		int orderID = 0;
		if(result.next()) {
			orderID = result.getInt("order_id");
		}
		return orderID;
		}
	
//	
//	public int getCustomerIDFromUsername(String username) {
//		
//		String sql ="Select customer_id FROM customer\n" + 
//				"JOIN users on users.user_id = customer.user_id\n" + 
//				"WHERE username = ?";
//		
//		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
//		
//		int customerID = 0;
//		if(result.next()) {
//			
//			customerID = result.getInt("customer_id");
//		}
//		
//		return customerID;
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
}
