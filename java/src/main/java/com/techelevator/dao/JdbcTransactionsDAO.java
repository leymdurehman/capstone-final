package com.techelevator.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.ArtPiece;
import com.techelevator.model.Fee;
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
		
		String sql = "UPDATE art_pieces SET is_sold = true, is_available = false WHERE art_id = ?";
		
		jdbcTemplate.update(sql, artID);	
		
	}


	@Override
	public void setFees(Fee newFees) {
		String sql = "UPDATE fees SET fee = ?, commission = ? WHERE fee_id = 1";
		jdbcTemplate.update(sql, newFees.getFee(), newFees.getCommission());
	}


	@Override
	public Fee getFees() {
		String sql = "SELECT fee_id, fee, commission FROM fees";
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql);
		row.next();
		
		Fee fee = new Fee();
		fee.setFeeID(row.getInt("fee_id"));
		fee.setFee(row.getDouble("fee"));
		fee.setCommission(row.getDouble("commission"));
		
		return fee;
	}
	
	
	
	
	
	
	
	
	
}
