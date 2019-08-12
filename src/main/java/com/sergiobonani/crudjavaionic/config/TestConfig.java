package com.sergiobonani.crudjavaionic.config;

import com.sergiobonani.crudjavaionic.services.DBService;
import com.sergiobonani.crudjavaionic.services.EmailService;
import com.sergiobonani.crudjavaionic.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public boolean instatiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}

	@Bean
	public EmailService emailService(){
		return new MockEmailService();
	}
}
