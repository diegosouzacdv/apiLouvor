package com.pv.louvor.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pv.louvor.model.Usuario;
import com.pv.louvor.model.dto.UsuarioRecuperarSenha;
import com.pv.louvor.repositories.UsuarioRepository;
import com.pv.louvor.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	
	public void sendNewPassword(UsuarioRecuperarSenha objDto) {
		Usuario usuario = usuarioRepository.findByEmail(objDto.getEmail());
		if( usuario == null) {
			throw new ObjectNotFoundException("E-mail não encontrado!");
		}
		usuario.setSenha(null);
		usuario.setSenha(pe.encode(objDto.getConfirmacaoSenha()));
		
		usuarioRepository.save(usuario);
		//emailService.sendNewPasswordEmail(usuario, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0 ) { //gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {//gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else { //gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
