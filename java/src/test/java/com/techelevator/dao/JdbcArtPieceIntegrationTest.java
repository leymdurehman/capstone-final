package com.techelevator.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.ArtPiece;

import junit.framework.Assert;

public class JdbcArtPieceIntegrationTest extends DAOIntegrationTest {

	private ArtPieceDAO artDao;
	private JdbcTemplate jdbcTemplate;

	private int artIdDummy = 99999;
	private String artistDummy = "TestArtist";
	private String dealerDummy = "TestDealer";
	private String titleDummy = "TestTitle";
	private LocalDate dateCreatedDummy = LocalDate.now();
	private double priceDummy = 99.99;
	private boolean isSoldDummy = false;
	private String imgFileNameDummy = "TestFileName";

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
		List<ArtPiece> createdArtPieceList = artDao.getAllListings();
		int listSize = createdArtPieceList.size();
		
		artDao.createListing(buildArtObject());
		artDao.createListing(buildArtObject());
		
		List<ArtPiece> testArtPieceList = artDao.getAllListings();
		
		Assert.assertEquals(listSize + 2, testArtPieceList.size());
	}

	private void insertTestArtist() {
		String sql = "INSERT INTO artist (artist_id, artist_name) VALUES (DEFAULT, ?) RETURNING artist_id";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, buildArtObject().getArtist());
		result.next();
		

		
		

	}

	private void insertTestDealer() {
		// sql for inserts

	}

	private ArtPiece buildArtObject() {
		ArtPiece testArt = new ArtPiece();

		testArt.setArtID(artIdDummy);
		testArt.setArtist(artistDummy);
		testArt.setDealer(dealerDummy);
		testArt.setTitle(titleDummy);
		testArt.setDateCreated(dateCreatedDummy);
		testArt.setPrice(priceDummy);
		testArt.setSold(isSoldDummy);
		testArt.setImgFileName(imgFileNameDummy);
		return testArt;
	}

}
