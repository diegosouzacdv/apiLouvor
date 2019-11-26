package com.pv.louvor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MusicaRepertorio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private MusicaRepertorioPK id = new MusicaRepertorioPK();
	
	@Column(name="rep_notaDia")
	private String notaDia;
	
	@Column(name="rep_ministro")
	private String ministro;
	
	public MusicaRepertorio() {
		
	}

	public MusicaRepertorio(Repertorio repertorio, Musica musica, String notaDia) {
		super();
		id.setRepertorio(repertorio);
		id.setMusica(musica);
		this.notaDia = notaDia;
	}
	
	@JsonIgnore
	public Repertorio getRepertorio() {
		return id.getRepertorio();
	}
	
	
	public void setRepertorio(Repertorio repertorio) {
		id.setRepertorio(repertorio);
	}

	public Musica getMusica() {
		return id.getMusica();
	}
	
	public void setMusica(Musica musica) {
		id.setMusica(musica);
	}
	
	
	public MusicaRepertorioPK getId() {
		return id;
	}

	public void setId(MusicaRepertorioPK id) {
		this.id = id;
	}

	public String getNotaDia() {
		return notaDia;
	}

	public void setNotaDia(String notaDia) {
		this.notaDia = notaDia;
	}

	public String getMinistro() {
		return ministro;
	}

	public void setMinistro(String ministro) {
		this.ministro = ministro;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		//builder.append(id);
		builder.append(id.toString());

		return builder.toString();
	}
}
