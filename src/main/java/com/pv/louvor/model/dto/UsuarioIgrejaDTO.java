package com.pv.louvor.model.dto;

import java.io.Serializable;

import com.pv.louvor.model.Igreja;
import com.pv.louvor.model.Usuario;

public class UsuarioIgrejaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Igreja igreja;
	
	public UsuarioIgrejaDTO() {
	}

	public UsuarioIgrejaDTO(Usuario usuario) {
		super();
		this.igreja = usuario.getIgreja();
	}

	public Igreja getIgreja() {
		return igreja;
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
	}

	@Override
	public String toString() {
		return "UsuarioIgrejaDTO [igreja=" + igreja + "]";
	}

	

}
