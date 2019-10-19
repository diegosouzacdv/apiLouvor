package com.pv.louvor.model.dto;


import java.io.Serializable;

import com.pv.louvor.model.Usuario;

public class UsuarioEmailDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
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
