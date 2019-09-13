package com.pv.louvor.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.pv.louvor.model.Repertorio;
import com.pv.louvor.model.Usuario;

public abstract class AbstractEmailService implements EmailService{
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Repertorio obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Repertorio obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo("");
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
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
			System.err.println(e);
		}
		
	}

	protected MimeMessage prepareMimeMessageFromRepertorio(Repertorio obj, String email) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		
		mmh.setTo(email);
		mmh.setCc(email);
		mmh.setReplyTo(email);
		mmh.setFrom(sender);
		mmh.setSubject("Novo Repertório! Para o dia: " + obj.getData() + " - " + obj.getDataSemana());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	
	}
	
	@Override
	public void sendNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(usuario, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Usuario usuario, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}
	
		
	}

