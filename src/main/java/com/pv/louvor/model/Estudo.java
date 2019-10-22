package com.pv.louvor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


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
	
	@Column(name="est_sample")
	private String sample;
	
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

	public String getSample() {
		return sample;
	}

	public void setSample(String sample) {
		this.sample = sample;
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

	@Override
	public String toString() {
		return "Estudo [letra=" + letra + ", cifra=" + cifra + ", bpm=" + bpm + ", guiaInstrumental=" + guiaInstrumental
				+ ", guiaVocal=" + guiaVocal + "]";
	}
	
	
}