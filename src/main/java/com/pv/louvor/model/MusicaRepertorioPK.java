package com.pv.louvor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class MusicaRepertorioPK implements Serializable{


	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="mus_id")
	private Musica musica;
	
	@ManyToOne
	@JoinColumn(name="rep_id")
	private Repertorio repertorio;
	

	public Repertorio getRepertorio() {
		return repertorio;
	}
	
	public void setRepertorio(Repertorio repertorio) {
		this.repertorio = repertorio;
	}
	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((musica == null) ? 0 : musica.hashCode());
		result = prime * result + ((repertorio == null) ? 0 : repertorio.hashCode());
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
		MusicaRepertorioPK other = (MusicaRepertorioPK) obj;
		if (musica == null) {
			if (other.musica != null)
				return false;
		} else if (!musica.equals(other.musica))
			return false;
		if (repertorio == null) {
			if (other.repertorio != null)
				return false;
		} else if (!repertorio.equals(other.repertorio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Música :");
		builder.append("\n");
		builder.append(musica.getNome() + " *GRUPO: " + musica.getGrupo().getNome() +  " *NOTA: " + musica.getNotaTocada() + " *CATEGORIA: " + musica.getCategorias().getNome() + "\n" );
		return builder.toString();
	}
	
	
}
