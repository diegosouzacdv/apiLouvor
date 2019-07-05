package com.pv.louvor.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.pv.louvor.model.Repertorio;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Repertorio obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Repertorio obj, String email);
	
	void sendHtmlEmail(MimeMessage msg);

}
