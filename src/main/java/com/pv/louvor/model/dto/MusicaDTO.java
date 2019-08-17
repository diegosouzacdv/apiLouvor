package com.pv.louvor.model.dto;


import java.io.Serializable;

import com.pv.louvor.model.Musica;

public class MusicaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String grupo;
	private String categoria;
	
	
	public MusicaDTO() {
		
	}
	
	
	public MusicaDTO(Musica obj) {
		id = obj.getId();
		nome = obj.getNome();
		grupo = obj.getGrupo().getNome();
		categoria = obj.getCategorias().getNome();
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

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
