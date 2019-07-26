package com.pv.louvor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pv.louvor.services.EmailService;
import com.pv.louvor.services.SmtpEmailService;

@Configuration
@Profile("prod")
public class ProdConfig {
	
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
