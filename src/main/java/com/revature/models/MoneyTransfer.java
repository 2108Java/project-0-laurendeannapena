package com.revature.models;

public class MoneyTransfer {
	
	private int transferId;
	private int toAccount;
	private int fromAccount;
	private double transferAmount;
	
	public MoneyTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MoneyTransfer(int toAccount, int fromAccount, double transferAmount) {
		super();
		this.toAccount = toAccount;
		this.fromAccount = fromAccount;
		this.transferAmount = transferAmount;
	}

	public MoneyTransfer(int transferId, int toAccount, int fromAccount, double transferAmount) {
		super();
		this.transferId = transferId;
		this.toAccount = toAccount;
		this.fromAccount = fromAccount;
		this.transferAmount = transferAmount;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public int getToAccount() {
		return toAccount;
	}

	public void setToAccount(int toAccount) {
		this.toAccount = toAccount;
	}

	public int getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(int fromAccount) {
		this.fromAccount = fromAccount;
	}

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		if(transferAmount > 0) {
			this.transferAmount = transferAmount;
		}
	}

}
