package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.ArtPiece;

public class JdbcArtPieceDao implements ArtPieceDAO{
	
	private JdbcTemplate jdbcTemplate; 
	public JdbcArtPieceDao (JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;;
	}

	@Override
	public int createListing(ArtPiece artPiece) {
		
		int artistId = getArtistId(artPiece.getArtist());
		int dealerId = getDealerId(artPiece.getDealer());
		
		String sql = "INSERT INTO art_pieces (art_id, title, date_created, price, img_file_name, artist_id, dealer_id) "
				+ "VALUES (DEFAULT, ?, CAST ( ? AS DATE ), ?, ?, ?, ?) RETURNING art_id";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, artPiece.getTitle(), artPiece.getDateCreated(), artPiece.getPrice(), artPiece.getImgFileName(), artistId, dealerId);
		
			int artID = 0;
			if(result.next()) {
				artID = result.getInt("art_id");
			}
		
			return artID;
			
			}

	@Override
	public List<ArtPiece> getAllListings() {
		String sql = "SELECT art_id, title, date_created, price, img_file_name, is_sold, artist.artist_id, artist_name, dealer.dealer_id, username " + 
				"FROM art_pieces " + 
				"JOIN artist ON artist.artist_id = art_pieces.artist_id " + 
				"JOIN dealer ON dealer.dealer_id = art_pieces.dealer_id " + 
				"JOIN users ON users.user_id = dealer.user_id";
		
		SqlRowSet row = jdbcTemplate.queryForRowSet(sql);
		
		List<ArtPiece> listOfArt = new ArrayList<ArtPiece>();
		while(row.next()) {
			ArtPiece art = mapRowToArt(row);
			listOfArt.add(art);
		}
		return listOfArt;
	}
	
	private ArtPiece mapRowToArt(SqlRowSet row) {
		
		ArtPiece art = new ArtPiece();
		
		art.setArtist(row.getString("artist_name"));
		art.setArtID(row.getInt("art_id"));
		art.setTitle(row.getString("title"));
		art.setDateCreated(row.getDate("date_created").toLocalDate());
		art.setPrice(row.getDouble("price"));
		art.setImgFileName(row.getString("img_file_name"));
		art.setSold(row.getBoolean("is_sold"));
		art.setDealerId(row.getInt("dealer_id"));
		art.setArtistId(row.getInt("artist_id"));
		art.setDealer(row.getString("username"));
		
		return art;
		
	}
	
	private int getArtistId(String artistName) {
		
		String sql = "SELECT artist_id FROM artist WHERE artist_name = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, artistName);
	}
	
	private int getDealerId(String dealerName) {
		
		String sql = "SELECT dealer_id FROM dealer JOIN users ON users.user_id = dealer.user_id WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, dealerName);
	}

}
