package com.project.demo.bank.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class BankDemoErrors {
	
	private String customMessage;
	private String exceptionMessage; 
	private LocalDateTime time;
	private HttpStatus status;
	public BankDemoErrors(){
		
	}
	public BankDemoErrors(String customMessage, String exceptionMessage, LocalDateTime time, HttpStatus status) {
		super();
		this.customMessage = customMessage;
		this.exceptionMessage=exceptionMessage;
		this.time = time;
		this.status = status;
	}
	public String getCustomMessage() {
		return customMessage;
	}
	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

}
