package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.UserText;
import com.demo.repo.UserTextDao;

@RestController
@RequestMapping("/demo")
public class HelloController {

	/**
	 * @param args
	 */
	@Autowired
	UserTextDao userTextDao;

	@RequestMapping("/hello")
	public @ResponseBody
	String hello(@RequestParam("testParam") String testParam) {
		UserText userText = new UserText();
		userText.setText(testParam);
		userTextDao.save(userText);
		return testParam;
	}
}
