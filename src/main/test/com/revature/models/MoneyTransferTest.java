package com.revature.models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MoneyTransferTest {

	MoneyTransfer t;
	
	@Before
	public void setupMoneyTransferTest() {
		t = new MoneyTransfer();
	}
	
	@Test
	public void setTransferAmount() {
		t.setTransferAmount(10);
		assertEquals(10, t.getTransferAmount());
	}
	
	@Test
	public void setTransferAmountNegative() {
		t.setTransferAmount(-10);
		assertEquals(0, t.getTransferAmount());
	}
}
