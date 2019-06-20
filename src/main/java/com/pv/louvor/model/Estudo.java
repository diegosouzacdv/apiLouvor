package com.pv.louvor.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estudo implements Serializable{

private static final long serialVersionUID = 1L;

	@Id
	@Column(name="est_nomeMusica")
	private Musica musica;
	
	@Column(name="est_letra")
	private String letra;
	
	@Column(name="est_cifra")
	private String cifra;
	
	@Column(name="est_bpm")
	private Integer bpm;
	
	@Column(name="est_guiaInstrumental")
	private List<String> guiaInstrumental;
	
	@Column(name="est_guiaVocal")
	private List<String> guiaVocal;
	
	@Column(name="est_tutorial")
	private List<Tutorial> tutorial;
	
	public Estudo() {
		
	}

	public Estudo(Musica musica, String letra, String cifra, Integer bpm, List<String> guiaInstrumental,
			List<String> guiaVocal, List<Tutorial> tutorial) {
		super();
		this.musica = musica;
		this.letra = letra;
		this.cifra = cifra;
		this.bpm = bpm;
		this.guiaInstrumental = guiaInstrumental;
		this.guiaVocal = guiaVocal;
		this.tutorial = tutorial;
	}

	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getCifra() {
		return cifra;
	}

	public void setCifra(String cifra) {
		this.cifra = cifra;
	}

	public Integer getBpm() {
		return bpm;
	}

	public void setBpm(Integer bpm) {
		this.bpm = bpm;
	}

	public List<String> getGuiaInstrumental() {
		return guiaInstrumental;
	}

	public void setGuiaInstrumental(List<String> guiaInstrumental) {
		this.guiaInstrumental = guiaInstrumental;
	}

	public List<String> getGuiaVocal() {
		return guiaVocal;
	}

	public void setGuiaVocal(List<String> guiaVocal) {
		this.guiaVocal = guiaVocal;
	}

	public List<Tutorial> getTutorial() {
		return tutorial;
	}

	public void setTutorial(List<Tutorial> tutorial) {
		this.tutorial = tutorial;
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
		Estudo other = (Estudo) obj;
		if (musica == null) {
			if (other.musica != null)
				return false;
		} else if (!musica.equals(other.musica))
			return false;
		return true;
	}
}