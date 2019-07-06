package com.pv.louvor.services;

import javax.mail.internet.MimeMessage;

import com.pv.louvor.model.Repertorio;

public interface EmailService {
	
	void sendOrderConfirmationHtmlEmail(Repertorio obj, String email);
	
	void sendHtmlEmail(MimeMessage msg);

}
