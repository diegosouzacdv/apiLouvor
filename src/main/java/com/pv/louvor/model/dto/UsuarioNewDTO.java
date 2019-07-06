package com.pv.louvor.model.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Nome é obrigatório")
	private String nome;
	@NotEmpty(message="Telefone é obrigatório")
	private String telefone;
	@NotEmpty(message="E-mail é obrigatório")
	private String email;
	@NotEmpty(message="Senha é obrigatório")
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
