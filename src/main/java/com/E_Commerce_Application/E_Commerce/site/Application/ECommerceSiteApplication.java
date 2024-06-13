package com.E_Commerce_Application.E_Commerce.site.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ECommerceSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceSiteApplication.class, args);
	}

}
