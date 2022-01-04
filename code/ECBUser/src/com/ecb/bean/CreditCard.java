package com.ecb.bean;

public class CreditCard {
	private String cardId;
	private double amount;
	
	public CreditCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreditCard(String cardId, double amount) {
		super();
		this.cardId = cardId;
		this.amount = amount;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double plusAmount (double money) {
		this.amount += money;
		return this.amount;
	}
	
	public double minusAmount (double money) {
		if (money > this.amount) {
			return -1;
		} else {
			this.amount -= money;
			return this.amount;
		}
	}
	public boolean equals(Object obj) {
		if (obj instanceof CreditCard) {
			return this.cardId.equals(((CreditCard) obj).cardId);
		}
		return false;
	}
}
