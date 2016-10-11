package com.demo.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.UserAccount;
import com.demo.model.UserMessage;
import com.demo.model.UserMessageDetail;
import com.demo.repo.UserAccountRepository;
import com.demo.repo.UserMessageRepository;
import com.demo.utility.UserMessageUtility;

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
	public String hello(@RequestParam("message") String message,
			@RequestParam("username") String userName,
			@RequestParam("parentMessageId") Long parentMessageId) {
		UserMessage userMessage = new UserMessage();
		userMessage.setMessage(message);
		userMessage.setParentMessageId(parentMessageId);
		UserAccount userAccount = userAccountRepo.findByUserName(userName);
		userMessage.setAccount(userAccount);
		userMessageRepo.save(userMessage);

		return "redirect:/login?inputParam=" + userAccount.getUserName();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("inputParam") String userName) {
		ModelAndView model = new ModelAndView();
		UserAccount userAccount = userAccountRepo.findByUserName(userName);
		if (null != userAccount) {
		} else {
			userAccount = new UserAccount();
			userAccount.setUserName(userName);
			userAccountRepo.save(userAccount);
		}
		if (userAccount.getUserMessageList() != null) {
			model.addObject("messages", new UserMessageUtility()
					.getUserMessageDetailTree(userAccount.getUserMessageList()));
		} else {
			model.addObject("messages", new ArrayList<UserMessageDetail>());
		}
		model.addObject("username", userName);
		model.addObject("userId", userAccount.getUserId());
		model.setViewName("hello");
		return model;

	}
}
