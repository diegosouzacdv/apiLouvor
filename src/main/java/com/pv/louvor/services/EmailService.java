package com.pv.louvor.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.pv.louvor.model.Repertorio;
import com.pv.louvor.model.Usuario;

public interface EmailService {
	
	void sendOrderConfirmationHtmlEmail(Repertorio obj, String email);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Usuario usuario, String newPass);
	
	void  sendEmail ( SimpleMailMessage  msg );

	void sendOrderConfirmationEmail(Repertorio obj);


}
