package com.project.demo.bank.account;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	private int accountNumber;
	private String accountType;
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Account(){
		
	}
	public Account(int accountNumber, String accountType, BigDecimal amount) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.amount=amount;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
