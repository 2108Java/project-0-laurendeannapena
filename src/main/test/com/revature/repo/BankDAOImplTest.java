package com.revature.repo;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class BankDAOImplTest {
	
	BankDAO database;
	
	@Before
	public void setupBankDAOTest(BankDAO database) {
		this.database = database;
	}

	@Test
	public void selectUserByUsername() {
		assertNotNull(database.selectUserByUsername("laurendeannapena"));
	}
}
