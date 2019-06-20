package com.pv.louvor.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tutorial implements Serializable{

private static final long serialVersionUID = 1L;

	@Id
	@Column(name="tut_nomeMusica")
	private Musica musica;
	
	@Column(name="tut_violao")
	private List<String> violao;
	
	@Column(name="tut_guitarra")
	private List<String> guitarra;
	
	@Column(name="tut_baixo")
	private List<String> baixo;
	
	@Column(name="tut_bateria")
	private List<String> bateria;
	
	@Column(name="tut_teclado")
	private List<String> teclado;
	
	public Tutorial() {
		
	}

	public Tutorial(Musica musica, List<String> violao, List<String> guitarra, List<String> baixo, List<String> bateria,
			List<String> teclado) {
		super();
		this.musica = musica;
		this.violao = violao;
		this.guitarra = guitarra;
		this.baixo = baixo;
		this.bateria = bateria;
		this.teclado = teclado;
	}

	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

	public List<String> getViolao() {
		return violao;
	}

	public void setViolao(List<String> violao) {
		this.violao = violao;
	}

	public List<String> getGuitarra() {
		return guitarra;
	}

	public void setGuitarra(List<String> guitarra) {
		this.guitarra = guitarra;
	}

	public List<String> getBaixo() {
		return baixo;
	}

	public void setBaixo(List<String> baixo) {
		this.baixo = baixo;
	}

	public List<String> getBateria() {
		return bateria;
	}

	public void setBateria(List<String> bateria) {
		this.bateria = bateria;
	}

	public List<String> getTeclado() {
		return teclado;
	}

	public void setTeclado(List<String> teclado) {
		this.teclado = teclado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((musica == null) ? 0 : musica.hashCode());
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
		Tutorial other = (Tutorial) obj;
		if (musica == null) {
			if (other.musica != null)
				return false;
		} else if (!musica.equals(other.musica))
			return false;
		return true;
	}
}
