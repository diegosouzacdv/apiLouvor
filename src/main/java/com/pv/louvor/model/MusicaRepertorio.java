package com.pv.louvor.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class MusicaRepertorio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@JsonIgnore
	private MusicaRepertorioPK id = new MusicaRepertorioPK();
	
	private Integer quantMusica;
	
	public MusicaRepertorio() {
		
	}

	public MusicaRepertorio(Musica musica, Repertorio repertorio) {
		super();
		this.id.setMusica(musica);
		this.id.setRepertorio(repertorio);
	}

	public Integer getQuantMusica() {
		return quantMusica;
	}

	public void setQuantMusica(Integer quantMusica) {
		this.quantMusica = quantMusica;
	}

	public MusicaRepertorioPK getId() {
		return id;
	}

	public void setId(MusicaRepertorioPK id) {
		this.id = id;
	}
	
	public Musica getMusica() {
		return id.getMusica();
	}
	
	@JsonIgnore
	public Repertorio getRepertorio() {
		return id.getRepertorio();
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
		MusicaRepertorio other = (MusicaRepertorio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
