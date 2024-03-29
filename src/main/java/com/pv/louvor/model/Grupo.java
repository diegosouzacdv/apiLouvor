package com.pv.louvor.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Grupo implements Serializable{

private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="gru_id")
	private Integer id;
	
	@NotEmpty(message="Nome é obrigatório")
	@Length(min=5, max=80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	@Column(name="gru_nome")
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "grupo")
	private List<Musica> musicas;
	
	@ManyToOne
	@JoinColumn(name = "gru_igreja")
	private Igreja igreja;
	
	@JsonIgnore
	private boolean ativo;
	
	@Column(name="gru_sede")
	private boolean sede;
	
	public Grupo() {
		
	}

	public Grupo(Integer id, String nome, boolean ativo, Igreja igreja, boolean sede) {
		super();
		this.setId(id);
		this.setNome(nome);
		this.setAtivo(ativo);
		this.setIgreja(igreja);
		this.setSede(sede);
		
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
	
	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Igreja getIgreja() {
		return igreja;
	}

	public void setIgreja(Igreja igreja) {
		this.igreja = igreja;
	}
	
	public boolean isSede() {
		return sede;
	}

	public void setSede(boolean sede) {
		this.sede = sede;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Grupo other = (Grupo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}	
	}