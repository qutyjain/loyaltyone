package com.demo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.demo.model.UserText;

@Component
public class UserTextDao {

	@Autowired
	private UserTextRepository userTesxtRepository;

	public Object findAll() {
		return userTesxtRepository.findAll();
	}

	public UserText findById(String id) {
		return userTesxtRepository.findOne(id);
	}

	public void save(UserText userText) {
		userTesxtRepository.save(userText);
	}

}
