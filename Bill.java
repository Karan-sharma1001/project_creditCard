package com.beans;

public class Bill {
	double total;
	String plan;
	double discount;
	double billamt;
	double balance;
	public Bill() {
		super();
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getBillamt() {
		return billamt;
	}
	public void setBillamt(double billamt) {
		this.billamt = billamt;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
