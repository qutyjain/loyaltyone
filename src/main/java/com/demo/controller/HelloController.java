package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.UserAccount;
import com.demo.model.UserMessage;
import com.demo.repo.UserAccountRepository;
import com.demo.repo.UserMessageRepository;

@RestController
@RequestMapping("/demo")
public class HelloController {

	/**
	 * @param args
	 */
	@Autowired
	UserMessageRepository userMessageRepo;

	@Autowired
	UserAccountRepository userAccountRepo;

	@RequestMapping(value = "/Message", method = RequestMethod.POST)
	public ModelAndView hello(@RequestParam("message") String message,
			@RequestParam("username") String userName) {
		UserMessage userMessage = new UserMessage();
		userMessage.setMessage(message);

		UserAccount userAccount = userAccountRepo.findByUserName(userName);
		userMessage.setAccount(userAccount);

		userMessageRepo.save(userMessage);
		ModelAndView model = new ModelAndView();
		model.addObject("messages", userAccount.getUserMessageList());
		model.addObject("username", userName);
		model.addObject("userId", userAccount.getUserId());
		model.setViewName("hello");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("inputParam") String userName) {
		UserAccount userAccount = userAccountRepo.findByUserName(userName);
		if (null != userAccount) {
		} else {
			userAccount = new UserAccount();
			userAccount.setUserName(userName);
			userAccountRepo.save(userAccount);
		}

		List<UserMessage> userMessageList = userAccount.getUserMessageList();
		ModelAndView model = new ModelAndView();
		model.addObject("messages", userMessageList);
		model.addObject("username", userName);
		model.addObject("userId", userAccount.getUserId());
		model.setViewName("hello");
		return model;

	}
}
