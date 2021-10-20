package com.project.demo.bank.customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.demo.bank.account.Account;
import com.project.demo.bank.account.AccountRepository;
import com.project.demo.bank.exception.CustomExceptionMessage;
import com.project.demo.bank.exception.InsufficientFundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AccountRepository accountRepository;

	public List<Customer> getAllCustomers() {

		List<Customer> customers = new ArrayList<Customer>();
		customerRepository.findAll().forEach(customers::add);
		return customers;
	}

	public Optional<Customer> getCustomerByID(String id) {

		return customerRepository.findById(id);
	}

	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	public void updateCustomer(Customer customer, String id) {
		customerRepository.save(customer);
	}

	public void deleteAll() {
		customerRepository.deleteAll();
	}

	public void deleteCustomerByID(String id) {
		customerRepository.deleteById(id);
	}

	public String transferFunds(String fromCustomerId, String toCustomerId, BigDecimal amountToTransfer) {
		Optional<Customer> fromCustomer = customerRepository.findById(fromCustomerId);
		Optional<Customer> toCustomer = customerRepository.findById(toCustomerId);
		if (fromCustomer.isPresent() && toCustomer.isPresent()) {
			BigDecimal existingBalance = fromCustomer.get().getAccount().getAmount();
			BigDecimal amount = toCustomer.get().getAccount().getAmount();
			if (amountToTransfer.intValue() > 0 && existingBalance.compareTo(amountToTransfer) >= 0) {
				System.out.println("before transfer");
				System.out.println("existing balance \t" + existingBalance);
				System.out.println("amount \t" + amount);
				amount = amount.add(amountToTransfer);
				toCustomer.get().getAccount().setAmount(amount);
				existingBalance = existingBalance.subtract(amountToTransfer);
				fromCustomer.get().getAccount().setAmount(existingBalance);
				System.out.println("after transfer");
				System.out.println("existing balance \t" + existingBalance);
				System.out.println("amount \t" + amount);
				List<Customer> customers = new ArrayList<>();
				customers.add(fromCustomer.get());
				customers.add(toCustomer.get());
				customerRepository.saveAll(customers);
				return "Successful transaction of amount " + amountToTransfer + " from customer id: " + fromCustomerId + " to customer id: ";
			} else {
				throw new InsufficientFundException(CustomExceptionMessage.INSUFFICIENT_FUND.name());
			}
		}
		return "No transaction occured between customer " + fromCustomerId +" and " + toCustomerId;
	}

	public boolean addAccountToCustomer(Customer customer, Account account) {
		if (customer.getAccount() == null || (customer.getAccount() != null
				&& !customer.getAccount().getAccountType().equalsIgnoreCase(account.getAccountType()))) {
			accountRepository.save(account);
			customer.setAccount(account);
			customerRepository.save(customer);
			return true;
		}
		return false;

	}

}
