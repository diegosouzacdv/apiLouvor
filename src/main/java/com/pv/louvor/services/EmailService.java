package com.pv.louvor.services;

import org.springframework.mail.SimpleMailMessage;

import com.pv.louvor.model.Repertorio;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Repertorio obj);
	
	void sendEmail(SimpleMailMessage msg);

}
