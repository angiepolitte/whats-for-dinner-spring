package com.angie.whats_for_dinner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class WhatsForDinnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhatsForDinnerApplication.class, args);
	}

}
