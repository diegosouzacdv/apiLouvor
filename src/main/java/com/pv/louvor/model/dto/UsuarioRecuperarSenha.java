package com.pv.louvor.model.dto;


import java.io.Serializable;

import com.pv.louvor.model.Usuario;

public class UsuarioRecuperarSenha implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String email;
	private String senha;
	private String confirmacaoSenha;
	
	
	public UsuarioRecuperarSenha() {
		
	}	
	
	public UsuarioRecuperarSenha(String email, String senha, String confirmacaoSenha) {
		super();
		this.email = email;
		this.senha = senha;
		this.confirmacaoSenha = confirmacaoSenha;
	}

	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}



	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
