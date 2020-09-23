package com.springionic.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.springionic.services.DBService;
import com.springionic.services.EmailService;
import com.springionic.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("{spring.jpa.hibernate.ddl-auto}") //pegando o valor em dev.properties
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if(!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
	}	
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
