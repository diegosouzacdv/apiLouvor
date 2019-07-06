package com.pv.louvor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name= "usuario")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usu_id")
	private Integer id;	
	
	@Embedded
	private Pessoa pessoa = new Pessoa();
	
	@ManyToMany
	@JoinTable(name="USUARIO_FUNCAO",
	joinColumns= @JoinColumn(name="usu_id"),
	inverseJoinColumns= @JoinColumn(name="fun_id"))
	private List<Funcao> funcao;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<Integer>();
	
	@ManyToOne
	@JoinColumn(name="igr_id")
	private Igreja igreja;
	
	private String email;
	
	@JsonIgnore
	private String senha;
	
	private boolean ativo = true;
	
	public Usuario() {
		addPerfil(Perfil.USUARIO);
	}

	public Usuario(Integer id, String nome, String telefone, String email, String senha) {
		super();
		this.id = id;
		this.pessoa.setNome(nome);
		this.pessoa.setTelefone(telefone);
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.USUARIO);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Funcao> getFuncao() {
		return funcao;
	}

	public void setFuncao(List<Funcao> funcao) {
		this.funcao = funcao;
	}

	public Igreja getIgreja() {
		return igreja;
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.ToEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
