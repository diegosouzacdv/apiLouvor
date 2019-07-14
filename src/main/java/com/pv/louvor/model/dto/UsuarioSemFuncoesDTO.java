package com.pv.louvor.model.dto;

import java.io.Serializable;

import com.pv.louvor.model.Usuario;

public class UsuarioSemFuncoesDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;

	private String telefone;

	private String email;
	
	public UsuarioSemFuncoesDTO() {
	}
	
	public UsuarioSemFuncoesDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getPessoa().getNome();
		telefone = obj.getPessoa().getNome();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UsuarioAtualizarDadosPessoaisDTO [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email="
				+ email + "]";
	}
	
}
