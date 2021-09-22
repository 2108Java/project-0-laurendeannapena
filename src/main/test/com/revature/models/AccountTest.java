package com.revature.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	Account a;
	
	@Before
	public void setupAccountTest() {
		a = new Account();
	}

	@Test
	public void setAccountBalancePositive() {
		a.setAccountBalance(10);
		assertEquals(10, a.getAccountBalance());
		
	}
	
	@Test
	public void setAccountBalanceNegative() {
		a.setAccountBalance(-10);
		assertEquals(0, a.getAccountBalance());
	}
}
