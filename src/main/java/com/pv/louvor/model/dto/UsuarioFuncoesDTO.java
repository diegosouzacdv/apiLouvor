package com.pv.louvor.model.dto;

import java.io.Serializable;

import com.pv.louvor.model.Usuario;

public class UsuarioFuncoesDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public UsuarioFuncoesDTO() {
	}
	
	public UsuarioFuncoesDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getPessoa().getNome();
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

	@Override
	public String toString() {
		return "UsuarioFuncoesDTO [nome=" + nome + "]";
	}	
}
