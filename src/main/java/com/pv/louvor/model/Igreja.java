package com.pv.louvor.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Igreja implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="igr_id")
	private Integer id;
	
	@NotEmpty(message="Nome é obrigatório")
	@Length(min=5, max=80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	@Column(name="igr_nome")
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "igreja")
	private List<Equipe> equipe;
	
	@JsonIgnore
	@OneToMany(mappedBy = "igreja")
	private List<Funcao> funcao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "igreja")
	private List<Grupo> grupo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "igreja")
	private List<Repertorio> repertorio;
	
	@JsonIgnore
	@OneToMany(mappedBy = "igreja")
	private List<Musica> musica;
	
	@JsonIgnore
	@OneToMany(mappedBy = "igreja")
	private List<Usuario> usuario;

	private boolean ativo;
	
	public Igreja() {
		
	}
	
	public Igreja(Integer id, String nome) {
		super();
		this.setId(id);
		this.setNome(nome);
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Equipe> getEquipe() {
		return equipe;
	}

	public void setEquipe(List<Equipe> equipe) {
		this.equipe = equipe;
	}

	public List<Grupo> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}

	public List<Repertorio> getRepertorio() {
		return repertorio;
	}

	public void setRepertorio(List<Repertorio> repertorio) {
		this.repertorio = repertorio;
	}

	public List<Musica> getMusica() {
		return musica;
	}

	public void setMusica(List<Musica> musica) {
		this.musica = musica;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public List<Funcao> getFuncao() {
		return funcao;
	}

	public void setFuncao(List<Funcao> funcao) {
		this.funcao = funcao;
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
		Igreja other = (Igreja) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

		
	
}
