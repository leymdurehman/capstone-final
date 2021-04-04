package com.techelevator.dao;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.ArtPiece;


public class JdbcArtPieceIntegrationTest extends DAOIntegrationTest{

	
	private ArtPieceDAO artDao;
	private JdbcTemplate jdbcTemplate;
	
	@BeforeClass 
	public static void setupData() {
		setupDataSource();
	}
	
	@AfterClass
	public static void closeData() throws SQLException {
		closeDataSource();
	}
	
	@After
	public void rollbackTransaction() throws SQLException {
		rollback();
	}
	
	@Before
	public void setup() {
		artDao = new JdbcArtPieceDao(getDataSource());
		jdbcTemplate = new JdbcTemplate(getDataSource());
		
		
		
	}
	
	@Test
	public void create_list() {
		insertTestArtist();
		insertTestDealer();
		ArtPiece testArt = buildArtObject();
		artDao.createListing(testArt);
		
		
	
	}
	
	@Test
	public void get_all_listing() {
		
	}
	
	
	
	private void insertTestArtist() {
	// sql for inserts
		
	}
	
	private void  insertTestDealer() {
	//sql for inserts
		
	}
	
	private ArtPiece buildArtObject() {
		
		ArtPiece testArt = new ArtPiece();
		
		// build dummy data but not artist id or dealer id
		
		return testArt;
	}
	
}
