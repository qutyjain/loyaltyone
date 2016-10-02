package com.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

	MockMvc mvc;
	@Autowired
	private WebApplicationContext webAppContext;
	String expectedResult = "ABC";
	String myUrl = "http://localhost:8080/demo/hello?inputText="
			+ expectedResult;

	@Test
	public void exampleTest() throws Exception {

		String expectedResult = "ABC";
		mvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(myUrl).accept(
						MediaType.TEXT_PLAIN_VALUE)).andReturn();

		Assert.assertEquals(result.getResponse().getContentAsString(),
				expectedResult);

	}

}
