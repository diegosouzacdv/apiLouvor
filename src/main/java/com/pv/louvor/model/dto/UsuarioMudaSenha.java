package com.pv.louvor.model.dto;


import java.io.Serializable;

public class UsuarioMudaSenha implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String senha;
	private String confirmacaoSenha;
	
	public UsuarioMudaSenha() {
		
	}	
	
	
	public UsuarioMudaSenha(String senha, String confirmacaoSenha) {
		super();
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
}
