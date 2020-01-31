package com.pv.louvor.model.dto;


import java.io.Serializable;
import java.util.Set;

import com.pv.louvor.model.Perfil;
import com.pv.louvor.model.Usuario;

public class UsuarioDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private boolean ativo;
	private String telefone;
	private Set<Perfil> perfil;
	private String email;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getPessoa().getNome();
		ativo = obj.isAtivo();
		telefone = obj.getPessoa().getTelefone();
		perfil = obj.getPerfis();
		email = obj.getEmail();
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<Perfil> getPerfil() {
		return perfil;
	}

	public void setPerfil(Set<Perfil> perfil) {
		this.perfil = perfil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
