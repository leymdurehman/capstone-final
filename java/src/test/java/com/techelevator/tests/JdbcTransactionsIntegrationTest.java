package com.techelevator.tests;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class JdbcTransactionsIntegrationTest extends DAOIntegrationTest{
	
	
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
	public void setup() {}
	
	
	
	
	
	

}
