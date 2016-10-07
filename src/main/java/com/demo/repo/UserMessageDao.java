package com.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.model.UserMessage;

@Component
public class UserMessageDao {

	@Autowired
	private UserMessageRepository userMessageRepository;

	public Object findAll() {
		return userMessageRepository.findAll();
	}

	public UserMessage findById(String id) {
		return userMessageRepository.getOne(id);
	}

	public void save(UserMessage userText) {
		userMessageRepository.save(userText);
	}

}
