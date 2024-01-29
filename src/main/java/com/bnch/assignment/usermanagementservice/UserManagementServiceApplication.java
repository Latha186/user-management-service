package com.bnch.assignment.usermanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserManagementServiceApplication {
	@Bean
	public RestTemplate restTemplateBean() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(UserManagementServiceApplication.class, args);
	}

}
