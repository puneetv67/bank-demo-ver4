package com.project.demo.bank.exception;

public class AccountAlreadyExistsWithCustomerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AccountAlreadyExistsWithCustomerException() {
	}
	public AccountAlreadyExistsWithCustomerException(String message) {
		super();
		this.message = message;
	}
}
