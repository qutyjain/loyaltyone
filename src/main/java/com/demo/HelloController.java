package com.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class HelloController {

	/**
	 * @param args
	 */
	@RequestMapping("/hello")
	public @ResponseBody String hello(@RequestParam("testParam")String testParam) {
		return testParam;
	}
}
