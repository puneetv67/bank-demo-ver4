package com.project.demo.bank.exception;

import java.time.LocalDateTime;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.project.demo.bank.exception.BankDemoErrors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(CustomExceptionMessage.MEDIA_CONVERSION_NOT_SUPPORTED.name());
		error.setExceptionMessage(ex.getMessage());
		error.setStatus(status);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(CustomExceptionMessage.INTERNAL_SERVER_ERROR.name());
		error.setExceptionMessage(ex.getMessage());
		error.setStatus(status);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(CustomExceptionMessage.MEDIA_TYPE_NOT_ACCEPTABLE.name());
		error.setExceptionMessage(ex.getMessage());
		error.setStatus(status);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(CustomExceptionMessage.MEDIA_TYPE_NOT_SUPPORTED.name());
		error.setExceptionMessage(ex.getMessage());
		error.setStatus(status);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BankDemoErrors error = new BankDemoErrors();
		error.setExceptionMessage(ex.getMessage());
		error.setCustomMessage(CustomExceptionMessage.METHOD_DOES_NOT_EXISTS_WITH_BANK_DEMO_API.name());
		error.setStatus(status);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(CustomExceptionMessage.PATH_VARIABLE_MISSING_IN_REQUEST.name());
		error.setExceptionMessage(ex.getMessage());
		error.setStatus(status);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(CustomExceptionMessage.BODY_MISSING_IN_REQUEST.name());
		error.setExceptionMessage(ex.getMessage());
		error.setStatus(status);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(CustomExceptionMessage.TYPE_MISMATCH.name());
		error.setExceptionMessage(ex.getMessage());
		error.setStatus(status);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.status(status).body(error);
	}
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<BankDemoErrors> handleCustomerNotFoundException(Exception ex){
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(ex.getMessage());
		error.setExceptionMessage(" " + ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.ok(error);
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<BankDemoErrors> handleAccountNotFoundException(Exception ex){
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(ex.getMessage());
		error.setExceptionMessage(" " + ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.ok(error);
	}
	
	@ExceptionHandler(AccountAlreadyExistsWithCustomerException.class)
	public ResponseEntity<BankDemoErrors> handleAccountAlreadyExistsWithCustomerException(Exception ex){
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(ex.getMessage());
		error.setExceptionMessage(" " + ex.getMessage());
		error.setStatus(HttpStatus.BAD_REQUEST);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.ok(error);
	}
	
	@ExceptionHandler(InsufficientFundException.class)
	public ResponseEntity<BankDemoErrors> handleInsufficientFundException(Exception ex){
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(ex.getMessage());
		error.setExceptionMessage(" " + ex.getMessage());
		error.setStatus(HttpStatus.OK);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.ok(error);
	}
	@ExceptionHandler
	public ResponseEntity<BankDemoErrors> handleAllOtherExceptions(Exception ex){
		BankDemoErrors error = new BankDemoErrors();
		error.setCustomMessage(CustomExceptionMessage.OTHER_EXCEPTIONS.name());
		error.setExceptionMessage(" " + ex.getMessage());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		error.setTime(LocalDateTime.now());
		return ResponseEntity.ok(error);
	}
}
