package com.pv.louvor.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Estudo implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Column(name="est_letra")
	private String letra;
	
	@Column(name="est_cifra")
	private String cifra;
	
	@Column(name="est_bpm")
	private Integer bpm;
	
	@Column(name="est_guia_instrumental")
	private String guiaInstrumental;
	
	@Column(name="est_guia_vocal")
	private String guiaVocal;

	@Embedded
	private List<Tutorial> tutorial;
	
	public Estudo() {
		
	}

	public Estudo(String letra, String cifra, Integer bpm, String guiaInstrumental, String guiaVocal
			) {
		super();
		this.letra = letra;
		this.cifra = cifra;
		this.bpm = bpm;
		this.guiaInstrumental = guiaInstrumental;
		this.guiaVocal = guiaVocal;
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

	public String getGuiaInstrumental() {
		return guiaInstrumental;
	}

	public void setGuiaInstrumental(String guiaInstrumental) {
		this.guiaInstrumental = guiaInstrumental;
	}

	public String getGuiaVocal() {
		return guiaVocal;
	}

	public void setGuiaVocal(String guiaVocal) {
		this.guiaVocal = guiaVocal;
	}

	public List<Tutorial> getTutorial() {
		return tutorial;
	}

	public void setTutorial(List<Tutorial> tutorial) {
		this.tutorial = tutorial;
	}
}