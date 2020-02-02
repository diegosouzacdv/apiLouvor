package com.pv.louvor.model.dto;


import java.io.Serializable;

import com.pv.louvor.model.Usuario;

public class UsuarioRecuperarSenha implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String email;
	private String telefone;
	
	public UsuarioRecuperarSenha() {
		
	}	
	
	public UsuarioRecuperarSenha(String email, String telefone) {
		super();
		this.email = email;
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
