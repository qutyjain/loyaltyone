package com.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.model.UserAccount;
import com.demo.model.UserMessage;

@Component
public class UserAccountDao {

	@Autowired
	private UserAccountRepository accountRepository;

	public Object findAll() {
		return accountRepository.findAll();
	}

	public UserAccount findById(String id) {
		return accountRepository.findOne(id);
	}

	public void save(UserAccount account) {
		accountRepository.save(account);
	}

}
