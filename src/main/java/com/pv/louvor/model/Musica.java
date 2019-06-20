package com.pv.louvor.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Musica implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mus_id")
	private Integer id;
	@Column(name="mus_nome")
	private String nome;
	@Column(name="mus_grupo")
	private Grupo grupo;
	@Column(name="mus_categoria")
	private Categoria categoria;
	@Column(name="mus_estudo")
	private Estudo estudo;
	@Column(name="mus_dataInserida")
	private Date dataInserida;
	@Column(name="mus_notaOriginal")
	private String notaOriginal;
	@Column(name="mus_notaTocada")
	private String notaTocada;
	

	public Musica() {
		
	}

	public Musica(Integer id, String nome, Grupo grupo, Categoria categoria, Estudo estudo, Date dataInserida,
			String notaOriginal, String notaTocada) {
		super();
		this.id = id;
		this.nome = nome;
		this.grupo = grupo;
		this.categoria = categoria;
		this.estudo = estudo;
		this.dataInserida = dataInserida;
		this.notaOriginal = notaOriginal;
		this.notaTocada = notaTocada;
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

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Estudo getEstudo() {
		return estudo;
	}

	public void setEstudo(Estudo estudo) {
		this.estudo = estudo;
	}

	public Date getDataInserida() {
		return dataInserida;
	}

	public void setDataInserida(Date dataInserida) {
		this.dataInserida = dataInserida;
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
	
	