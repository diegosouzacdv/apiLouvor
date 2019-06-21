package com.pv.louvor.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
@JsonIgnoreProperties
@JsonIgnoreType
public class Musica implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mus_id")
	private Integer id;
	
	@Column(name="mus_nome")
	private String nome;
	
	@ManyToMany()
	@JoinTable(name= "MUSICA_GRUPO",
		joinColumns = @JoinColumn(name = "mus_id"),
		inverseJoinColumns = @JoinColumn(name = "gru_id"))
	private List<Grupo> grupo;
	
	@ManyToMany()
	@JoinTable(name= "MUSICA_CATEGORIA",
		joinColumns = @JoinColumn(name = "mus_id"),
		inverseJoinColumns = @JoinColumn(name = "cat_id"))
	private List<Categoria> categorias;
	
	@Column(name="mus_estudo")
	@Embedded
	private Estudo estudo;
	
	@Column(name="mus_tutorial")
	@Embedded
	private Tutorial tutorial;
	
	@Column(name="mus_dataInserida")
	private LocalDate dataInserida;
	
	@Column(name="mus_notaOriginal")
	private String notaOriginal;
	
	@Column(name="mus_notaTocada")
	private String notaTocada;	
	
	private boolean ativo;
	

	public Musica() {
		
	}

	public Musica(Integer id, String nome, LocalDate dataInserida,
			String notaOriginal, String notaTocada, boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataInserida = dataInserida;
		this.notaOriginal = notaOriginal;
		this.notaTocada = notaTocada;
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

	public List<Grupo> getGrupo() {
		return grupo;
	}

	public void setGrupo(List<Grupo> grupo) {
		this.grupo = grupo;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public String getNotaOriginal() {
		return notaOriginal;
	}

	public void setNotaOriginal(String notaOriginal) {
		this.notaOriginal = notaOriginal;
	}

	public String getNotaTocada() {
		return notaTocada;
	}

	public void setNotaTocada(String notaTocada) {
		this.notaTocada = notaTocada;
	}

	public Estudo getEstudo() {
		return estudo;
	}

	public void setEstudo(Estudo estudo) {
		this.estudo = estudo;
	}

	public LocalDate getDataInserida() {
		return dataInserida;
	}

	public void setDataInserida(LocalDate dataInserida) {
		this.dataInserida = dataInserida;
	}
	
	public Tutorial getTutorial() {
		return tutorial;
	}

	public void setTutorial(Tutorial tutorial) {
		this.tutorial = tutorial;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
		Musica other = (Musica) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
	
	