package com.techelevator.tenmo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.tenmo.dao.TransferSqlDAO;

class TenmoIntegrationTests {
	
	private static SingleConnectionDataSource dataSource;
	private TransferSqlDAO dao;
	private static JdbcTemplate jdbcTemplate;

	@BeforeAll //Do the stuff before any tests are run, but do it once
	static void setUpBeforeClass() throws Exception {
		
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/tenmo");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
		jdbcTemplate = new JdbcTemplate(dataSource);
	
	}

	@AfterAll // The activities we want to complete after everything is run, but only once
	static void tearDownAfterClass() throws Exception {
		dataSource.destroy();
		
	}

	@BeforeEach //Before one specific test
	void setUp() throws Exception {
		
		dao = new TransferSqlDAO(jdbcTemplate);
	}

	@AfterEach // After one specific test
	void tearDown() throws Exception {
		
		dataSource.getConnection().rollback();
	}

	@Test
	void test() {
		
		// The starting balance of a new account is going to be $1,000
		// This is testing the getBalance method in the TransferSqlDAO class
		
		
	
		
	}

}
