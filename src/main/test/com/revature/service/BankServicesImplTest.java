package com.revature.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.revature.models.Account;

public class BankServicesImplTest {

	BankServices service;
	
	@Before
	public void setupServicesImpl(BankServices service) {
		this.service = service;
	}
	
	@Test
	public void authenticate() {
		assertTrue(service.authenticate("laurendeannapena", "2108Java") );
	}
	
	@Test
	public void getUserByUsername() {
		assertNotNull(service.getUserByUsername("laurendeanapena"));
	}
	
	@Test
	public void listAccountsByUser() {
		List<Account> list = new ArrayList<>();
		assertNotNull(service.listAccountsByUser(list, 1));
		
	}
	
	@Test
	public void viewPendingAccounts() {
		List<Account> list = new ArrayList<>();
		assertNotNull(service.viewPendingAccounts(list));
	}
	
	
	@Test
	public void getListOfCustomers() {
		assertNotNull(service.getListOfCustomers());
	}

}
