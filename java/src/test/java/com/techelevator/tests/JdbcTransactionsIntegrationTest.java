package com.techelevator.tests;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.dao.ArtPieceDAO;
import com.techelevator.dao.JdbcTransactionsDAO;
import com.techelevator.dao.TransactionsDao;


public class JdbcTransactionsIntegrationTest extends DAOIntegrationTest{
	
	private TransactionsDao transactionsDao;
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
	private static final int COSTUMER_ID_DUMMY = 66666;
	private static final int USER_ID_COSTUMER_DUMMY = 55555;
	private static final String COSTUMER_USERNAME_DUMMY = "DUMMYcostumerUser";
	private static final String PASS_COSTUMER_DUMMY = "DUMMYpass";
	private static final String ROLE_COSTUMER_DUMMY = "ROLE_USER";
	private static final int ART_ID_DUMMY = 00000;
	


	
	
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
		
		transactionsDao = new JdbcTransactionsDAO(jdbcTemplate);
		
		// create user 
		
		jdbcTemplate.update("INSERT INTO users (user_id, username, password_hash, role) VALUES (?, ?, ?, ?);", USER_ID_DUMMY, USERNAME_DUMMY, PASS_DUMMY, ROLE_DUMMY);
		jdbcTemplate.update("INSERT INTO users (user_id, username, password_hash, role) VALUES (?, ?, ?, ?);", USER_ID_DUMMY, USERNAME_DUMMY, PASS_DUMMY, ROLE_DUMMY);
	
		// create dealer
		
		jdbcTemplate.update("INSERT INTO dealer (dealer_id, user_id) VALUES (?, ?)", DEALER_ID_DUMMY, USER_ID_DUMMY);
			
		// create artist id
		
		 jdbcTemplate.update("INSERT INTO artist (artist_id, artist_name) VALUES (?, ?)", ARTIST_ID_DUMMY, ARTIST_NAME_DUMMY);
		 jdbcTemplate.update("INSERT INTO art_pieces (art_id, title, date_created, price, img_file_name, artist_id, dealer_id) VALUES (?, ?, ?, ?, ?, ?, ?);", 
				 				ART_ID_DUMMY, TITLE_DUMMY, dateCreatedDummy, PRICE_DUMMY, IMG_FILE_DUMMY, ARTIST_ID_DUMMY, DEALER_ID_DUMMY);
		 // insert transaction
		 jdbcTemplate.update("INSERT INTO transactions (order_id, customer_id, art_id, date_of_sale) VALUES (default, ?, ?, ?);", COSTUMER_ID_DUMMY);
	}
	
	
	
	
	
	

}
