package com.pv.louvor.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.pv.louvor.model.Repertorio;
import com.pv.louvor.model.dto.UsuarioEmailDTO;

public abstract class AbstractEmailService implements EmailService{
	
	@Autowired
	private UsuarioService usuario;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
			sm.setBcc(email.getEmail());
			sm.setCc(email.getEmail());
			sm.setFrom(sender);
			sm.setSubject("Repertório do dia: " + obj.getData());
			sm.setSentDate(new Date(System.currentTimeMillis()));
			sm.setText(obj.toString());
			System.err.println(obj.toString());
		}
		return sm;
	}
	
	protected String htmlFromTemplatePedido(Repertorio obj) {
		Context context = new Context();
		context.setVariable("repertorio", obj);
		return templateEngine.process("email/confirmacaoRepertorio", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Repertorio obj, String email) {
		System.err.println(obj.toString());
		MimeMessage mm;
		try {
			mm = prepareMimeMessageFromRepertorio(obj, email);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromRepertorio(Repertorio obj, String email) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		
		mmh.setTo(email);
		mmh.setCc(email);
		mmh.setReplyTo(email);
		mmh.setFrom(sender);
		mmh.setSubject("Novo Repertório! Para o dia: " + obj.getData());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	
	}
	
		
	}

