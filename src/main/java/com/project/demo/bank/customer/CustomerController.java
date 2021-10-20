package com.project.demo.bank.customer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.bank.account.Account;
import com.project.demo.bank.account.AccountService;
import com.project.demo.bank.exception.AccountAlreadyExistsWithCustomerException;
import com.project.demo.bank.exception.AccountNotFoundException;
import com.project.demo.bank.exception.CustomExceptionMessage;
import com.project.demo.bank.exception.CustomerNotFoundException;

@RestController
@RequestMapping(value = "bank-demo")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private AccountService accountService;

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = getCustomerService().getAllCustomers();
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "request to return all customers");
		if (customers != null && !customers.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).headers(header).body(customers);
		} else
			throw new CustomerNotFoundException(CustomExceptionMessage.NO_CUSTOMERS_EXISTS_WITH_BANK.name());
		// return
		// ResponseEntity.status(HttpStatus.NO_CONTENT).headers(header).build();
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity<Optional<Customer>> getCustomerByID(@PathVariable String id) {
		Optional<Customer> customerByID = getCustomerService().getCustomerByID(id);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "request to return customer associated with provided id");
		if (customerByID.isPresent()) {
			return new ResponseEntity<Optional<Customer>>(customerByID, header, HttpStatus.OK);
		} else
			throw new CustomerNotFoundException(CustomExceptionMessage.NO_CUSTOMER_EXISTS_WITH_PROVIDED_ID.name());
		// return
		// ResponseEntity.status(HttpStatus.NO_CONTENT).headers(header).build();

	}

	@RequestMapping(method = RequestMethod.POST, value = "/customers")
	public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "request to add a customer");
		return ResponseEntity.status(HttpStatus.CREATED).headers(header).build();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/customers/{id}")
	public ResponseEntity<Void> updateCustomer(@RequestBody Customer customer, @PathVariable String id) {
		customerService.updateCustomer(customer, id);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "this request will either update existing customer or create a new customer");
		return ResponseEntity.status(HttpStatus.CREATED).headers(header).build();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/customers")
	public ResponseEntity<Void> deleteAll() {
		customerService.deleteAll();
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "request to delete all customers");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/customers/{id}")
	public ResponseEntity<Void> deleteCustomerByID(@PathVariable String id) {
		customerService.deleteCustomerByID(id);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "request to delete customer associated with provided id");
		return ResponseEntity.status(HttpStatus.OK).headers(header).build();
	}

	@RequestMapping(method=RequestMethod.POST , value="/customers/{id}/addAccount")
	public ResponseEntity<Customer> addAccountToCustomer(@PathVariable String id, @RequestBody Account account) {
		Optional<Customer> customer = getCustomerService().getCustomerByID(id);
		if (customer.isPresent()) {
			boolean outcome = getCustomerService().addAccountToCustomer(customer.get(), account);
			if (!outcome) {
				throw new AccountAlreadyExistsWithCustomerException(
						CustomExceptionMessage.SIMILAR_ACCOUNT_ALREADY_ATTACHED_WITH_CUSTOMER.name());
			} else {
				HttpHeaders header = new HttpHeaders();
				header.add("desc", "request to add account to customer");
				return ResponseEntity.status(HttpStatus.OK).headers(header).body(customer.get());
			}
		} else {
			throw new CustomerNotFoundException(CustomExceptionMessage.NO_CUSTOMER_EXISTS_WITH_PROVIDED_ID.name());
		}
	}

	@RequestMapping(method=RequestMethod.POST ,value = "/customers/{customerId}/accounts/{accountId}")
	public ResponseEntity<Customer> addAccountToCustomer(@PathVariable String customerId, @PathVariable int accountId) {
		boolean outcome=false;
		Optional<Customer> customer = getCustomerService().getCustomerByID(customerId);
		Optional<Account> account = accountService.getAccountByID(accountId);
		if (customer.isPresent() && account.isPresent()) {
			outcome = getCustomerService().addAccountToCustomer(customer.get(), account.get());
		} else if (!customer.isPresent()) {
			throw new CustomerNotFoundException(CustomExceptionMessage.NO_CUSTOMER_EXISTS_WITH_PROVIDED_ID.name());
		} else if (!account.isPresent()){
			throw new AccountNotFoundException(CustomExceptionMessage.NO_ACCOUNT_EXISTS_WITH_PROVIDED_ID.name());
		}
		if (!outcome) {
			throw new AccountAlreadyExistsWithCustomerException(
					CustomExceptionMessage.SIMILAR_ACCOUNT_ALREADY_ATTACHED_WITH_CUSTOMER.name());
		} else {
			HttpHeaders header = new HttpHeaders();
			header.add("desc", "request to add account to customer");
			return ResponseEntity.status(HttpStatus.OK).headers(header).body(customer.get());
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/customers/transfers/{fromCustomerId}/{toCustomerId}/{amount}")
	public ResponseEntity<String> transferFunds(@PathVariable String fromCustomerId, @PathVariable String toCustomerId,
			@PathVariable BigDecimal amount) {
		String transferFunds = getCustomerService().transferFunds(fromCustomerId, toCustomerId, amount);
		HttpHeaders header = new HttpHeaders();
		header.add("desc", "request to transfer funds between customers provided");
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(transferFunds);
	}
}
