package com.pv.louvor.model.dto;

import java.io.Serializable;

public class UsuarioNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String telefone;
	private String email;
	private String senha;
	
	public UsuarioNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	
	
	
	

}
