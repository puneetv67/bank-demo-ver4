package com.project.demo.bank.account;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AccountControllerTest {

	AccountController accountController;
	@Autowired
	private Mockito mockito;
	@Autowired
	private AccountService accountService;
	@MockBean
	private AccountRepository accountRepository;
	@Test
	public void test() {
		fail("Not yet implemented"); // TODO
	}
	
	/*@Test
	public List<Account> getAllAccountsTest(){
		
		when(accountController.getAllAccounts()).thenReturn
	}*/
}
