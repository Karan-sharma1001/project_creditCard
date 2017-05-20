package com.beans;

import java.util.Date;

public class Transaction {
	int transaction_id;
	int user_id;
	Date transaction_date;
	String vendor;
	String location;
	int amount;
	String remarks;

	public Transaction(int transaction_id, int user_id, Date transaction_date,
			String vendor, String location, int amount, String remarks) {
		super();
		this.transaction_id = transaction_id;
		this.user_id = user_id;
		this.transaction_date = transaction_date;
		this.vendor = vendor;
		this.location = location;
		this.amount = amount;
		this.remarks = remarks;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public String getVendor() {
		return vendor;
	}

	public String getLocation() {
		return location;
	}

	public int getAmount() {
		return amount;
	}

	public String getRemarks() {
		return remarks;
	}

}
