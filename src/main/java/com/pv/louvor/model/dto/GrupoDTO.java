package com.pv.louvor.model.dto;


import java.io.Serializable;

import com.pv.louvor.model.Grupo;

public class GrupoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	
	
	public GrupoDTO() {
		
	}
	
	public GrupoDTO(Grupo obj) {
		id = obj.getId();
		nome = obj.getNome();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

}
