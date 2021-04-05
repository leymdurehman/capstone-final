package com.techelevator.tests;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.dao.ArtPieceDAO;
import com.techelevator.dao.JdbcArtPieceDao;
import com.techelevator.model.ArtPiece;

import org.junit.Assert;



public class JdbcArtPieceIntegrationTest  extends DAOIntegrationTest {

 
	private ArtPieceDAO artDao;
	private JdbcTemplate jdbcTemplate;
	private static final int ARTIST_ID_DUMMY = 88888;
	private static final int DEALER_ID_DUMMY = 77777;
	private static final int USER_ID_DUMMY = 11111;
	private static final String USERNAME_DUMMY = "DUMMYuser";
	private static final String PASS_DUMMY = "DUMMYpass";
	private static final String ROLE_DUMMY = "ROLE_ADMIN";
	private static final String ARTIST_NAME_DUMMY = "DUMMYArtist";
	private static final String TITLE_DUMMY = "DUMMYTitle";
	private LocalDate dateCreatedDummy = LocalDate.now();
	private static final double PRICE_DUMMY = 99.99;
	private static final String IMG_FILE_DUMMY = "DUMMYimagefile";

	@BeforeClass
    public static void setupData() {
    	setupDataSource();
    	
    }
    
    @AfterClass
    public static void closeData() throws SQLException {
    	closeDataSource();
    }


    
    @After
    public void roll() throws SQLException {
        rollback();
    }
	@Before
	public void setup() {
		
	    DataSource dataSource = this.getDataSource();
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		artDao = new JdbcArtPieceDao(jdbcTemplate);
		
		// create user 
		
		jdbcTemplate.update("INSERT INTO users (user_id, username, password_hash, role) VALUES (?, ?, ?, ?);", USER_ID_DUMMY, USERNAME_DUMMY, PASS_DUMMY, ROLE_DUMMY);
		
		// create dealer
		
		jdbcTemplate.update("INSERT INTO dealer (dealer_id, user_id) VALUES (?, ?)", DEALER_ID_DUMMY, USER_ID_DUMMY);
			
		// create artist id
		
		 jdbcTemplate.update("INSERT INTO artist (artist_id, artist_name) VALUES (?, ?)", ARTIST_ID_DUMMY, ARTIST_NAME_DUMMY);
		
		
		}
	
		
	
	
	@Test
	public void create_listing() {
		
		ArtPiece testArtPiece = testArtPiece();
		
		int artID = artDao.createListing(testArtPiece);

		Assert.assertTrue(artID > 0);
		
	}

	@Test
	public void get_all_listing() {
		
		ArtPiece testArtPiece = testArtPiece();
		
		artDao.createListing(testArtPiece);
		
		List <ArtPiece> listings = artDao.getAllListings();
			
		Assert.assertEquals(1, listings.size());
	
	}

	
	private ArtPiece testArtPiece() {
		
		ArtPiece testArt = new ArtPiece();

		testArt.setArtist(ARTIST_NAME_DUMMY);
		testArt.setDealer(USERNAME_DUMMY);
		testArt.setTitle(TITLE_DUMMY);
		testArt.setDateCreated(dateCreatedDummy);
		testArt.setPrice(PRICE_DUMMY);
		testArt.setImgFileName(IMG_FILE_DUMMY);
		return testArt;
	}

	
	
}
