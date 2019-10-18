package com.pv.louvor.model.dto;


import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UsuarioEmailDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento Obrigatório")
	@Email(message="Email Inválido")
	private String email;
	
	
	public UsuarioEmailDTO() {
		
	}
	
	
	public UsuarioEmailDTO(String email) {
		super();
		this.email = email;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
