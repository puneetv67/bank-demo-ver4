package com.project.demo.bank.customer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.project.demo.bank.account.Account;

@Entity
public class Customer {

	private String firstName;
	private String lastName;
	@Id
	private String customerID;
	
	@OneToOne
	private Account account;
	public Customer(){
		
	}
	public Customer(String firstName, String lastName, String customerID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerID = customerID;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Customer(String firstName, String lastName, String customerID, Account account) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerID = customerID;
		this.account=account;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

}
