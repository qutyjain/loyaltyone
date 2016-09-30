package com.demo;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Starter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Starter.class, args);

		System.out.println("Started loading the beans from application context:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

}
