package com.revature.models;

public class Account {

	private int accountId;
	private int userIdOne;
	private int userIdTwo;
	private String accountType;
	private double accountBalance;
	private boolean isApproved;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int userIdOne, String accountType, double accountBalance, boolean isApproved) {
		super();
		this.userIdOne = userIdOne;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.isApproved = isApproved;
	}

	public Account(int userIdOne, int userIdTwo, String accountType, double accountBalance, boolean isApproved) {
		super();
		this.userIdOne = userIdOne;
		this.userIdTwo = userIdTwo;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.isApproved = isApproved;
	}

	public Account(int accountId, int userIdOne, int userIdTwo, String accountType, double accountBalance,
			boolean isApproved) {
		super();
		this.accountId = accountId;
		this.userIdOne = userIdOne;
		this.userIdTwo = userIdTwo;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.isApproved = isApproved;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getUserIdOne() {
		return userIdOne;
	}

	public void setUserIdOne(int userIdOne) {
		this.userIdOne = userIdOne;
	}

	public int getUserIdTwo() {
		return userIdTwo;
	}

	public void setUserIdTwo(int userIdTwo) {
		this.userIdTwo = userIdTwo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double differenceInBalance) {
		
		//prevent overdrafting
		if(this.accountBalance + differenceInBalance >= 0) {
			this.accountBalance += differenceInBalance;
		}//end if statement
		
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
}
