package com.pv.louvor.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.pv.louvor.model.Repertorio;
import com.pv.louvor.model.dto.UsuarioEmailDTO;
import com.pv.louvor.repositories.UsuarioRepository;
import com.pv.louvor.resources.UsuarioResource;

public abstract class AbstractEmailService implements EmailService{
	
	@Autowired
	UsuarioService usuario;
	
	@Value("${default.sender}")
	private String sender;
		
	@Override
	public void sendOrderConfirmationEmail(Repertorio obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromRepertorio(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromRepertorio(Repertorio obj) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		for(UsuarioEmailDTO email: usuario.buscarTodosEmails()) {
			sm.setTo(email.getEmail());
			sm.setFrom(sender);
			sm.setSubject("Repertório do dia: " + obj.getData());
			sm.setSentDate(new Date(System.currentTimeMillis()));
			sm.setText(obj.toString());
		}
		
		return null;
	}
		
	}

