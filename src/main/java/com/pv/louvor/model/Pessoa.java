package com.pv.louvor.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Nome é obrigatório")
	private String nome;
	
	@NotEmpty(message="Telefone é obrigatório")
	private String telefone;
	
	public Pessoa() {
		
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
}