package com.pv.louvor.model.dto;

import java.io.Serializable;
import java.util.List;

import com.pv.louvor.model.Funcao;
import com.pv.louvor.model.Usuario;

public class UsuarioNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String nome;

	private String telefone;
	
	private String email;
	
	private String senha;
	
	private int igreja;
	
	private List<Funcao> funcao;
	
	
	public UsuarioNewDTO() {
	}
	
	public UsuarioNewDTO(Usuario obj) {
		id= obj.getId();
		nome = obj.getPessoa().getNome();
		telefone = obj.getPessoa().getTelefone();
		email = obj.getEmail();
		senha = obj.getSenha();
		funcao = obj.getFuncao();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getIgreja() {
		return igreja;
	}

	public void setIgreja(int igreja) {
		this.igreja = igreja;
	}
	
	public List<Funcao> getFuncao() {
		return funcao;
	}

	public void setFuncao(List<Funcao> funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "UsuarioNewDTO [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", senha="
				+ senha + ", igreja=" + igreja + "]";
	}
	
	
}
